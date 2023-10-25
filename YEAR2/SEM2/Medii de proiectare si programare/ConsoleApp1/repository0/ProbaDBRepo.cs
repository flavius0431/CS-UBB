using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleApp1.domain;
using ConsoleApp1.repository;
using log4net;
using System.Data;

namespace ConsoleApp1.repository0
{
    public class ProbaDBRepo : IProba
    {
        private static readonly ILog log = LogManager.GetLogger("ProbaDBRepo");

        IDictionary<String, string> props;

        public ProbaDBRepo(IDictionary<String, string> props)
        {
            log.Info("Creating ProbaDBRepo");
            this.props = props;
        }
        public Proba Delete(long id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Proba> FindAll()
        {
            throw new NotImplementedException();
        }

        public Proba FindOne(long id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection(props);
            Proba proba = null;
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select varstamin,varstamax,proba from Proba  where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {

                        int varstamin = dataR.GetInt32(0);
                        int varstamax = dataR.GetInt32(1);
                        string proba1 = dataR.GetString(2);
                        proba = new Proba(varstamin, varstamax,proba1);
                        proba.id = id;
                        log.InfoFormat("Exiting findOne with value {0}", proba);
                        return proba;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", proba);
            return proba;
        }

  

    public Proba Save(Proba entity)
        {
            throw new NotImplementedException();
        }

    public Proba Update(Proba entity)
        {
           throw new NotImplementedException();
        }
    }
}
