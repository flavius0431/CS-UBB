using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using log4net;
using ConsoleApp1.domain;
using ConsoleApp1.repository;

namespace ConsoleApp1.repository0
{
    public class OficiuPersoanaDBRepo :IOficiuPersoana
    {
        private static readonly ILog log = LogManager.GetLogger("OficiuPersonaDBRepo");

        IDictionary<String, string> props;

        public  OficiuPersoanaDBRepo(IDictionary<String, string> props)
        {
            log.Info("Creating OficiuPersoanaDBRepo");
            this.props = props;
        }
       public  OficiuPersoana Delete(long id)
        {
            throw new NotImplementedException();
        }

         public IEnumerable<OficiuPersoana> FindAll()
        {
            throw new NotImplementedException();
        }

        public OficiuPersoana FindOne(long id)
        {
            throw new NotImplementedException();
        }

        public  OficiuPersoana Save(OficiuPersoana entity)
        {
            throw new NotImplementedException();
        }

        public OficiuPersoana Update(OficiuPersoana entity)
        {
            throw new NotImplementedException();
        }


        public OficiuPersoana LogIn(String user, String password)
        {
            OficiuPersoana oficiuPersoana = null;
            log.InfoFormat("Entering LogIn with values {0},{1}", user, password);

            IDbConnection con =DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select id from OficiuPersoana where user=@user and password=@password";
                IDbDataParameter paramUsername = comm.CreateParameter();
                paramUsername.ParameterName = "@user";
                paramUsername.Value = user;
                comm.Parameters.Add(paramUsername);

                IDbDataParameter paramParola = comm.CreateParameter();
                paramParola.ParameterName = "@password";
                paramParola.Value = password;
                comm.Parameters.Add(paramParola);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        OficiuPersoana oficiuPersoana1 = new OficiuPersoana(user, password,"");
                        oficiuPersoana1.id = id;
                        log.InfoFormat("Exiting LogIn with value {0}", oficiuPersoana1);
                        return oficiuPersoana1;
                    }
                }
            }

            log.InfoFormat("Exiting LogIn with value {0}", null);
            return oficiuPersoana;
        }
    }
}
    

