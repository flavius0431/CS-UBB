using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaUI.domain
{
    public class OficiuPersoana:Entity<long>
    {
        /// <summary>
        /// public si cu litere mari
        /// </summary>
        public string User { get; set; }

        public string Password { get; set; }

        public string Oficiu { get; set; }

        public OficiuPersoana(string User, string Password, string Oficiu)
        {
            this.User = User;   
            this.Password = Password;
            this.Oficiu = Oficiu;
        }

    }
}
