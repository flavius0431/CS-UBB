using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.domain
{
    [Serializable]
    public class Entity<ID>
    {

        public ID id { get; set; }

    }
}
