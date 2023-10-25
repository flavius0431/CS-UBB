using Microsoft.Data.SqlClient;
using System.Data;

namespace Festivaluri222_1
{
    public partial class Form1 : Form
    {
        string connectionString = @"Server=DESKTOP-ER9407B\SQLEXPRESS;DataBase=GestiuneFestivaluri;
        Integrated Security=true;TrustServerCertificate=true;";
        DataSet ds = new DataSet();
        SqlDataAdapter adapter = new SqlDataAdapter();



        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            MessageBox.Show("Hello Windows Forms App .NET 6!");
            try
            {
                using(SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();
                    MessageBox.Show(connection.State.ToString());
                    adapter.SelectCommand = new SqlCommand("SELECT * FROM Festivaluri;", connection);
                    adapter.Fill(ds, "Festivaluri");
                    dataGridView1.DataSource = ds.Tables["Festivaluri"];
                }

            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                using(SqlConnection connection = new SqlConnection(connectionString))
                {
                    //setam noua conexiune pentru comanda select a SqlDataAdapter-ului
                    adapter.SelectCommand.Connection = connection;
                    if(ds.Tables.Contains("Festivaluri"))
                       ds.Tables["Festivaluri"].Clear();
                    adapter.Fill(ds, "Festivaluri");
                }
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

       
    }
}