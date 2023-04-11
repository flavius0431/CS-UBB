using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TemaUI.domain;
using TemaUI.service;
namespace TemaUI
{
    public partial class Form2 : Form
    {
        private Service Srv;
        private Form1 Login;

        public void setSetvice(Service service)
        {
            this.Srv = service;
            dataGridProbe.DataSource = this.Srv.GetDTOProba();

        }

        public Form2(Form1 form1)
        {
            InitializeComponent();
            this.Login= form1;
        }

        private void delogarebutton_Click(object sender, EventArgs e)
        {
            this.Login.Visible = true;
            this.Close();
            
        }

        private void dataGridProbe_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
           
        }

        private void dataGridParticipant_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void dataGridProbe_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            int rowIndex = (int)dataGridProbe.SelectedCells[0].RowIndex;
            String proba = (String)dataGridProbe.Rows[rowIndex].Cells["Proba1"].Value;
            int varstamin = (int)dataGridProbe.Rows[rowIndex].Cells["VarstaMin"].Value;
            int varstamax = (int)dataGridProbe.Rows[rowIndex].Cells["VarstaMax"].Value;
            if (proba != null )
            {
                dataGridParticipant.DataSource = this.Srv.FindProba(varstamin,varstamax,proba);
            }
            
        }

        private void inregistrarebutton_Click(object sender, EventArgs e)
        {
            String nume = numetextBox.Text;
            int varsta = Int32.Parse(varstatextBox.Text);
            long cnp = Int64.Parse(cnptextBox.Text);

            int rowIndex = (int)dataGridProbe.SelectedCells[0].RowIndex;
            String proba = (String)dataGridProbe.Rows[rowIndex].Cells["Proba1"].Value;
            int varstamin = (int)dataGridProbe.Rows[rowIndex].Cells["VarstaMin"].Value;
            int varstamax = (int)dataGridProbe.Rows[rowIndex].Cells["VarstaMax"].Value;
            long id = (long)dataGridProbe.Rows[rowIndex].Cells["Id"].Value;
            Proba proba1 = new Proba(varstamin, varstamax, proba);
            proba1.id=id;
            Participant participant = this.Srv.SearchwithCNP(cnp.ToString());
            if(participant == null )
            {
                MessageBox.Show("Participantul nu exista");

                List<Proba> probas= new List<Proba>();
                probas.Add(proba1);
                Participant p2 = new Participant(nume, varsta, cnp.ToString(), probas);
                this.Srv.AddParticipant(p2);
                dataGridParticipant.DataSource = this.Srv.FindProba(varstamin, varstamax, proba);
                dataGridProbe.DataSource = this.Srv.GetDTOProba();
            }
            else
            {
                MessageBox.Show("Participantul exista");
                List<Proba> probe = new List<Proba>();
                probe = participant.Proba;
                probe.Add(proba1);
                participant.Proba = probe;
                this.Srv.UpdateParticipant(participant);
                dataGridProbe.DataSource = this.Srv.GetDTOProba();
                dataGridParticipant.DataSource = this.Srv.FindProba(varstamin, varstamax, proba);
            }
        }
    }
}
