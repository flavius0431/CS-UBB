using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaUI.domain
{
    public class ProbaDTO
    {
        public int VarstaMin { get; set; }

        public int VarstaMax { get; set; }

        public string Proba1 { get; set; }

        public int nrParticipanti { get; set; }

        public long Id { get; set; }

        public ProbaDTO(int varstaMin, int varstaMax, string proba1, int nrParticipanti, long id)
        {
            this.VarstaMin = varstaMin;
            this.VarstaMax = varstaMax;
            this.Proba1 = proba1;
            this.nrParticipanti = nrParticipanti;
            this.Id = id;
        }

 
    }
}
