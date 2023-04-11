using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaUI.domain;
using TemaUI.repository0;
using log4net;
using System.Data;

namespace TemaUI.repository0
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
            log.InfoFormat("Entering findAll with value {0}", "0");
            IDbConnection con = DBUtils.getConnection(props);
            List<Proba> probs = new List<Proba>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select id, varstamin,varstamax,proba from Proba";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        int varstamin = dataR.GetInt32(1);
                        int varstamax = dataR.GetInt32(2);
                        String proba = dataR.GetString(3);

                        Proba proba1 = new Proba(varstamin, varstamax, proba);
                        proba1.id = idV;
                        probs.Add(proba1);
                    }
                }
                log.InfoFormat("Exiting findAll with value {0}", probs);
                return probs;

            }
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
