using System.Configuration;
using TemaUI.service;

namespace TemaUI
{
    public partial class Form1 : Form
    {
        Service Serv;

        public void setSevice(Service service)
        {
            this.Serv = service;
        }
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void buttonLogin_Click(object sender, EventArgs e)
        {
            String user = userTextbox.Text;
            String password = passwordTextbox.Text;
            if(user =="" || password == "")
            {
                MessageBox.Show("Nu ati intodus date!");
            }
            else
            {
                
                    if (Serv.Login(user, password)!=null)
                    {
                        Form2 homepage = new Form2(this);
                        homepage.setSetvice(Serv);
                        homepage.Show(this);
                        this.Visible = false;
                        userTextbox.Text="";
                        passwordTextbox.Text= "";

                    }
                /*}catch(Exception ex)
                {
                    MessageBox.Show(ex.ToString());
                }*/
            }
           
        }
    }
}