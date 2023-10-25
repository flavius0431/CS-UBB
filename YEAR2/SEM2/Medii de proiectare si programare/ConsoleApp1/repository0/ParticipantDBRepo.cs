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
    public class ParticipantDBRepo : IParticipant
    {
        private static readonly ILog log = LogManager.GetLogger("ParticioantDBRepo");

        IDictionary<String, string> props;

        public ProbaDBRepo probaDBRepo;

        public ParticipantDBRepo(IDictionary<String, string> props, ProbaDBRepo probaDBRepo)
        {
            log.Info("Creating ParticipantDBRepo");
            this.props = props;
            this.probaDBRepo = probaDBRepo;
        }
        public  Participant Delete(long id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Participant> FindAll()
        {
            log.InfoFormat("Entering findAll with value {0}","0");
            IDbConnection con = DBUtils.getConnection(props);
            IList<Participant> participantR = new List<Participant>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select id,nume, varsta, probe from Participant";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        int varsta = dataR.GetInt32(2);
                        String probe = dataR.GetString(3);
                        List<Proba> probas = new List<Proba>();
                        string[] str = probe.Split(',');
                        foreach (string i in str)
                        {
                            Proba prob = probaDBRepo.FindOne(long.Parse(i));
                            probas.Add(prob);
                        }
                        Participant participant = new Participant(nume, varsta, probas);
                        participant.id = idV;
                        participantR.Add(participant);

                    }
                }
                log.InfoFormat("Exiting findAll with value {0}", participantR);
                return participantR;

            }
        }

        public Participant FindOne(long id)
        {
            throw new NotImplementedException();
        }

        public Participant Save(Participant entity)
        {
            log.InfoFormat("Entering save with value {}");
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into Participant  values (@nume,@varsta,@probe)";
     

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@desc";
                paramNume.Value = entity.Nume;
                comm.Parameters.Add(paramNume);

                var paramVarsta = comm.CreateParameter();
                paramVarsta.ParameterName = "@varsta";
                paramVarsta.Value = entity.Varsta;
                comm.Parameters.Add(paramVarsta);

                IDbDataParameter paramProbe = comm.CreateParameter();
                paramProbe.ParameterName = "@probe";
                string prob = "";
                foreach( Proba proba in entity.Proba)
                {
                    prob = proba.id + ",";
                }
                if(prob.Length > 2) {
                    prob= prob.Substring(0, prob.Length-1);
                }
                paramProbe.Value = prob;
                comm.Parameters.Add(paramProbe);

                var result = comm.ExecuteNonQuery();
                if(result == 0)
                {
                    System.Console.WriteLine("nu s-a salvat");
                }
            }
            log.InfoFormat("Exiting save with value {0}", entity);
            return entity;

        }

        public Participant Update(Participant entity)
        {
            throw new NotImplementedException();
        }
    }
}
