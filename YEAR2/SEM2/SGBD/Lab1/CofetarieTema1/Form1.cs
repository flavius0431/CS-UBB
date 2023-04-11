using Microsoft.Data.SqlClient;
using System.Data;
using System.Linq.Expressions;

namespace CofetarieTema1
{
    public partial class Form1 : Form
    {
        SqlConnection conn = new SqlConnection(@"Server=DESKTOP-ER9407B\SQLEXPRESS;Database=Cofetarie;Integrated Security=true;TrustServerCertificate=true;") ;
        DataSet ds = new DataSet();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        //intermediat intre sursa si controale sa afisam doar anuite inregistrari
        BindingSource parentBS = new BindingSource();
        BindingSource childBS = new BindingSource();

        
      
        public Form1()
        {
            InitializeComponent();
        }
        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                conn.Open();
                MessageBox.Show(conn.State.ToString());
                parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM TipClient", conn);
                childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Client", conn);
                //executam comanda de select
                parentAdapter.Fill(ds, "TipClient");
                childAdapter.Fill(ds, "Client");

                //vrem sa afisam datele in rabel (data gried view)
                parentBS.DataSource = ds.Tables["TipClient"];
                dataGridParent.DataSource = parentBS;

                DataColumn parentColumn = ds.Tables["TipClient"].Columns["cod_tip_client"];
                DataColumn childColumn = ds.Tables["Client"].Columns["cod_tip_client"];
                DataRelation relation = new DataRelation("FK_TipClient_Client", parentColumn, childColumn);

                ds.Relations.Add(relation);
                childBS.DataSource = parentBS;
                childBS.DataMember = "FK_TipClient_Client";
                dataGridChild.DataSource = childBS;

                codTipClient.DataBindings.Add("Text", childBS, "cod_tip_client");
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
                
            }
            finally { conn.Close();  }

        }


        private void ButtonDelete_Click(object sender, EventArgs e)
        {
            try
            {
                conn.Open();

                int rowIndex = (int)dataGridChild.SelectedCells[0].RowIndex;
                int codp = (int)dataGridChild.Rows[rowIndex].Cells[0].Value;

                childAdapter.DeleteCommand = new SqlCommand("DELETE FROM Client WHERE cod_client=@cod_client", conn);
                childAdapter.DeleteCommand.Parameters.AddWithValue("@cod_client", codp);

                childAdapter.DeleteCommand.ExecuteNonQuery();


                MessageBox.Show("Clientul a fost sters cu succes", "Notificare", MessageBoxButtons.OK, MessageBoxIcon.Information);
                ds.Tables["Client"].Clear();
                childAdapter.Fill(ds, "Client");
            }catch(Exception ex) {
                MessageBox.Show(ex.Message); 
            }
            finally { conn.Close(); }
        }

        private void ButtonUpdate_Click(object sender, EventArgs e)
        {
            try { 
             
                conn.Open();
                int rowIndex = (int)dataGridChild.SelectedCells[0].RowIndex;
                int codp = (int)dataGridChild.Rows[rowIndex].Cells[0].Value;

                childAdapter.UpdateCommand = new SqlCommand("UPDATE Client SET nume_client= @numeclient, adresa_client = @adresaclient, cod_tip_client=@cod_tip_client WHERE cod_client = @cod_client",conn);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@numeclient",numeClient.Text);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@adresaclient",adresaClient.Text);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@cod_tip_client", codTipClient.Text);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@cod_client", codp);

                childAdapter.UpdateCommand.ExecuteNonQuery();

                MessageBox.Show("Clientul a fost actualizat cu succes", "Notificare", MessageBoxButtons.OK, MessageBoxIcon.Information);
                ds.Tables["Client"].Clear();
                childAdapter.Fill(ds, "Client");
            }
            catch(Exception ex) { MessageBox.Show(ex.Message); }
            finally { conn.Close(); }

        }

        private void ButtonAdd_Click(object sender, EventArgs e)
        {
            try
            {

                conn.Open();
                int rowIndex = (int)dataGridParent.SelectedCells[0].RowIndex;
                int codp = (int)dataGridParent.Rows[rowIndex].Cells[0].Value;
                codTipClient.Text =""+ codp.ToString();

                childAdapter.InsertCommand = new SqlCommand("INSERT INTO Client  (nume_client, adresa_client, cod_tip_client) VALUES(@numeclient, @adresaclient,@cod_tip_client)", conn);
                childAdapter.InsertCommand.Parameters.AddWithValue("@numeclient", numeClient.Text);
                childAdapter.InsertCommand.Parameters.AddWithValue("@adresaclient", adresaClient.Text);
                childAdapter.InsertCommand.Parameters.AddWithValue("@cod_tip_client", codp);
                

                childAdapter.InsertCommand.ExecuteNonQuery();

                MessageBox.Show("Clientul a fost inserat cu succes", "Notificare", MessageBoxButtons.OK, MessageBoxIcon.Information);
                ds.Tables["Client"].Clear();
                childAdapter.Fill(ds, "Client");
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
            finally { conn.Close(); }

        }

        private void DataGridChild_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            codTipClient.Enabled = true;

            Update.Visible = true;
            Delete.Visible = true;
            Add.Visible = false;

            int rowIndex = (int)dataGridChild.SelectedCells[0].RowIndex;
            String nume_client = (String)dataGridChild.Rows[rowIndex].Cells["nume_client"].Value;
            String adresa_client = (String)dataGridChild.Rows[rowIndex].Cells["adresa_client"].Value;
            Int32 cod_tip_client = (Int32)dataGridChild.Rows[rowIndex].Cells["cod_tip_client"].Value;
            numeClient.Text=nume_client.ToString();
            adresaClient.Text=adresa_client.ToString();
            codTipClient.Text=cod_tip_client.ToString();

        }

        private void DataGridParent_CellMouseClick(object sender,DataGridViewCellMouseEventArgs e) { 
            codTipClient.Enabled = false;

            Add.Visible = true;
            Update.Visible = false;
            Delete.Visible = false;

            numeClient.Text = "";
            adresaClient.Text = "";

        }
        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        
    }
}