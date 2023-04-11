namespace TemaUI
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridProbe = new System.Windows.Forms.DataGridView();
            this.dataGridParticipant = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.numetextBox = new System.Windows.Forms.TextBox();
            this.varstatextBox = new System.Windows.Forms.TextBox();
            this.cnptextBox = new System.Windows.Forms.TextBox();
            this.inregistrarebutton = new System.Windows.Forms.Button();
            this.delogarebutton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridProbe)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridParticipant)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridProbe
            // 
            this.dataGridProbe.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridProbe.Location = new System.Drawing.Point(60, 59);
            this.dataGridProbe.Name = "dataGridProbe";
            this.dataGridProbe.RowHeadersWidth = 51;
            this.dataGridProbe.RowTemplate.Height = 25;
            this.dataGridProbe.Size = new System.Drawing.Size(568, 150);
            this.dataGridProbe.TabIndex = 0;
            this.dataGridProbe.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridProbe_CellContentClick);
            this.dataGridProbe.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.dataGridProbe_CellMouseClick);
            // 
            // dataGridParticipant
            // 
            this.dataGridParticipant.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridParticipant.Location = new System.Drawing.Point(60, 245);
            this.dataGridParticipant.Name = "dataGridParticipant";
            this.dataGridParticipant.RowHeadersWidth = 51;
            this.dataGridParticipant.Size = new System.Drawing.Size(240, 150);
            this.dataGridParticipant.TabIndex = 1;
            this.dataGridParticipant.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridParticipant_CellContentClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(262, 19);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(126, 15);
            this.label1.TabIndex = 2;
            this.label1.Text = "Inregistrare participant";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(444, 245);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(40, 15);
            this.label2.TabIndex = 3;
            this.label2.Text = "Nume";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(444, 289);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(38, 15);
            this.label3.TabIndex = 4;
            this.label3.Text = "Varsta";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(444, 337);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(31, 15);
            this.label4.TabIndex = 5;
            this.label4.Text = "CNP";
            // 
            // numetextBox
            // 
            this.numetextBox.Location = new System.Drawing.Point(528, 247);
            this.numetextBox.Name = "numetextBox";
            this.numetextBox.Size = new System.Drawing.Size(100, 23);
            this.numetextBox.TabIndex = 6;
            // 
            // varstatextBox
            // 
            this.varstatextBox.Location = new System.Drawing.Point(528, 289);
            this.varstatextBox.Name = "varstatextBox";
            this.varstatextBox.Size = new System.Drawing.Size(100, 23);
            this.varstatextBox.TabIndex = 7;
            // 
            // cnptextBox
            // 
            this.cnptextBox.Location = new System.Drawing.Point(528, 334);
            this.cnptextBox.Name = "cnptextBox";
            this.cnptextBox.Size = new System.Drawing.Size(100, 23);
            this.cnptextBox.TabIndex = 8;
            // 
            // inregistrarebutton
            // 
            this.inregistrarebutton.Location = new System.Drawing.Point(444, 402);
            this.inregistrarebutton.Name = "inregistrarebutton";
            this.inregistrarebutton.Size = new System.Drawing.Size(75, 23);
            this.inregistrarebutton.TabIndex = 9;
            this.inregistrarebutton.Text = "Inregistrare";
            this.inregistrarebutton.UseVisualStyleBackColor = true;
            this.inregistrarebutton.Click += new System.EventHandler(this.inregistrarebutton_Click);
            // 
            // delogarebutton
            // 
            this.delogarebutton.Location = new System.Drawing.Point(553, 402);
            this.delogarebutton.Name = "delogarebutton";
            this.delogarebutton.Size = new System.Drawing.Size(75, 23);
            this.delogarebutton.TabIndex = 10;
            this.delogarebutton.Text = "Delogare";
            this.delogarebutton.UseVisualStyleBackColor = true;
            this.delogarebutton.Click += new System.EventHandler(this.delogarebutton_Click);
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.delogarebutton);
            this.Controls.Add(this.inregistrarebutton);
            this.Controls.Add(this.cnptextBox);
            this.Controls.Add(this.varstatextBox);
            this.Controls.Add(this.numetextBox);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataGridParticipant);
            this.Controls.Add(this.dataGridProbe);
            this.Name = "Form2";
            this.Text = "Form2";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridProbe)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridParticipant)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DataGridView dataGridProbe;
        private DataGridView dataGridParticipant;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private TextBox numetextBox;
        private TextBox varstatextBox;
        private TextBox cnptextBox;
        private Button inregistrarebutton;
        private Button delogarebutton;
    }
}