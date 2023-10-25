using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.domain
{
   public class Proba:Entity<long>
    {
        public int VarstaMin { get; set; }

        public int VarstaMax { get; set; }

        public string Proba1 { get; set; }

        public Proba(int varstaMin, int varstaMax, string proba1)
        {
            this.VarstaMin = varstaMin;
            this.VarstaMax = varstaMax;
            this.Proba1 = proba1;
        }

        public override string ToString()
        {
            return this.VarstaMin+ " "+Proba1;
        }
    }
}
