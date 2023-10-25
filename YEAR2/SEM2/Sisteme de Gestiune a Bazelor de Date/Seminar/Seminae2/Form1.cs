using Microsoft.Data.SqlClient;
using System.Data;

namespace Seminae2
{
    public partial class Form1 : Form
    {
        string connectionString = @"Server=DESKTOP-ER9407B\SQLEXPRESS;Database=Seminar2;Integrated Security=true;TrustServerCertificate=true;";
        DataSet ds = new DataSet();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
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
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();
                    MessageBox.Show(connection.State.ToString());
                    parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Functii;", connection);
                    childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Angajati;", connection);
                    parentAdapter.Fill(ds, "Functii");
                    childAdapter.Fill(ds, "Angajati");

                    parentBS.DataSource = ds.Tables["Functii"];
                    dataGridViewParent.DataSource = parentBS;
                }
            }catch(Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click_1(object sender, EventArgs e)
        {

        }

        private void dataGridChild_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}