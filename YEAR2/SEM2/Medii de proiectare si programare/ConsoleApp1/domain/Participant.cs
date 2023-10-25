using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.domain
{
    public class Participant:Entity<long>
    {
        public string Nume { get; set; }

        public int Varsta { get; set; }

        public List<Proba> Proba { get; set; }


        public Participant(string Nume, int Varsta, List<Proba> Proba)
        {
            this.Nume = Nume;
            this.Varsta = Varsta;
            this.Proba = Proba;
        }
        public override string ToString()
        {
            return Nume+" "+Varsta;
        }
    }
}
