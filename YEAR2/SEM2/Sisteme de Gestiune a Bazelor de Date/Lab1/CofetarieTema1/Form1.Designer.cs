using Microsoft.Data.SqlClient;
namespace CofetarieTema1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridParent = new System.Windows.Forms.DataGridView();
            this.dataGridChild = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.TipClient = new System.Windows.Forms.Label();
            this.Update = new System.Windows.Forms.Button();
            this.Delete = new System.Windows.Forms.Button();
            this.numeClient = new System.Windows.Forms.TextBox();
            this.adresaClient = new System.Windows.Forms.TextBox();
            this.codTipClient = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.Add = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridParent)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridChild)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridParent
            // 
            this.dataGridParent.BackgroundColor = System.Drawing.Color.Thistle;
            this.dataGridParent.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridParent.Location = new System.Drawing.Point(52, 117);
            this.dataGridParent.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dataGridParent.Name = "dataGridParent";
            this.dataGridParent.RowHeadersWidth = 51;
            this.dataGridParent.RowTemplate.Height = 29;
            this.dataGridParent.Size = new System.Drawing.Size(376, 288);
            this.dataGridParent.TabIndex = 0;
            this.dataGridParent.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.DataGridParent_CellMouseClick);
            // 
            // dataGridChild
            // 
            this.dataGridChild.BackgroundColor = System.Drawing.Color.Thistle;
            this.dataGridChild.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridChild.Location = new System.Drawing.Point(539, 117);
            this.dataGridChild.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dataGridChild.Name = "dataGridChild";
            this.dataGridChild.RowHeadersWidth = 51;
            this.dataGridChild.Size = new System.Drawing.Size(485, 288);
            this.dataGridChild.TabIndex = 1;
            this.dataGridChild.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.DataGridChild_CellMouseClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(52, 56);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(57, 15);
            this.label1.TabIndex = 2;
            this.label1.Text = "TipClient:";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // TipClient
            // 
            this.TipClient.AutoSize = true;
            this.TipClient.Location = new System.Drawing.Point(539, 56);
            this.TipClient.Name = "TipClient";
            this.TipClient.Size = new System.Drawing.Size(41, 15);
            this.TipClient.TabIndex = 3;
            this.TipClient.Text = "Client:";
            this.TipClient.Click += new System.EventHandler(this.label2_Click);
            // 
            // Update
            // 
            this.Update.Location = new System.Drawing.Point(343, 502);
            this.Update.Name = "Update";
            this.Update.Size = new System.Drawing.Size(75, 23);
            this.Update.TabIndex = 4;
            this.Update.Text = "Update";
            this.Update.UseVisualStyleBackColor = true;
            this.Update.Click += new System.EventHandler(this.ButtonUpdate_Click);
            // 
            // Delete
            // 
            this.Delete.Location = new System.Drawing.Point(343, 464);
            this.Delete.Name = "Delete";
            this.Delete.Size = new System.Drawing.Size(75, 23);
            this.Delete.TabIndex = 5;
            this.Delete.Text = "Delete";
            this.Delete.UseVisualStyleBackColor = true;
            this.Delete.Click += new System.EventHandler(this.ButtonDelete_Click);
            // 
            // numeClient
            // 
            this.numeClient.Location = new System.Drawing.Point(144, 417);
            this.numeClient.Name = "numeClient";
            this.numeClient.Size = new System.Drawing.Size(151, 23);
            this.numeClient.TabIndex = 6;
            // 
            // adresaClient
            // 
            this.adresaClient.Location = new System.Drawing.Point(144, 464);
            this.adresaClient.Name = "adresaClient";
            this.adresaClient.Size = new System.Drawing.Size(151, 23);
            this.adresaClient.TabIndex = 7;
            // 
            // codTipClient
            // 
            this.codTipClient.Location = new System.Drawing.Point(144, 503);
            this.codTipClient.Name = "codTipClient";
            this.codTipClient.Size = new System.Drawing.Size(151, 23);
            this.codTipClient.TabIndex = 8;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.label2.Location = new System.Drawing.Point(52, 420);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(76, 17);
            this.label2.TabIndex = 9;
            this.label2.Text = "Nume Client";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.label3.Location = new System.Drawing.Point(51, 464);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(79, 17);
            this.label3.TabIndex = 10;
            this.label3.Text = "Adresa Client";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.label4.Location = new System.Drawing.Point(46, 503);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(84, 17);
            this.label4.TabIndex = 11;
            this.label4.Text = "Cod Tip Client";
            // 
            // Add
            // 
            this.Add.Location = new System.Drawing.Point(343, 420);
            this.Add.Name = "Add";
            this.Add.Size = new System.Drawing.Size(75, 23);
            this.Add.TabIndex = 12;
            this.Add.Text = "Add";
            this.Add.UseVisualStyleBackColor = true;
            this.Add.Click += new System.EventHandler(this.ButtonAdd_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightCyan;
            this.ClientSize = new System.Drawing.Size(1068, 558);
            this.Controls.Add(this.Add);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.codTipClient);
            this.Controls.Add(this.adresaClient);
            this.Controls.Add(this.numeClient);
            this.Controls.Add(this.Delete);
            this.Controls.Add(this.Update);
            this.Controls.Add(this.TipClient);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataGridChild);
            this.Controls.Add(this.dataGridParent);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridParent)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridChild)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DataGridView dataGridParent;
        private DataGridView dataGridChild;
        private Label label1;
        private Label TipClient;
        private Button Update;
        private Button Delete;
        private TextBox numeClient;
        private TextBox adresaClient;
        private TextBox codTipClient;
        private Label label2;
        private Label label3;
        private Label label4;
        private Button Add;
    }
}