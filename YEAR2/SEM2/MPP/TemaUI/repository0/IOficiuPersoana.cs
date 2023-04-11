using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaUI.domain;
using TemaUI.repository0;

namespace TemaUI.repository0
{
    public interface IOficiuPersoana : IRepository<long,OficiuPersoana>
    {
        public OficiuPersoana LogIn(String user, String password);
    }
}
