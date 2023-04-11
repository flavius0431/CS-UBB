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
                comm.CommandText = "select id, nume, varsta, cnp, probe from Participant";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        int varsta = dataR.GetInt32(2);
                        long cnp = dataR.GetInt64(3);
                        String probe = dataR.GetString(4);
                        List<Proba> probas = new List<Proba>();
                        string[] str = probe.Split(',');
                        for (int i1 = 0; i1 < str.Length-1; i1++)
                        {
                            string i = str[i1];
                            Proba prob = probaDBRepo.FindOne(long.Parse(i));
                            probas.Add(prob);
                        }
                        Participant participant = new Participant(nume, varsta,""+cnp, probas);
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
                comm.CommandText = "insert into Participant(id, nume, varsta, cnp, probe) values (@id,@nume,@varsta,@cnp,@probe)";
                Random rnd = new Random();
                var paramid = comm.CreateParameter();
                paramid.ParameterName = "@id";
                paramid.Value = rnd.Next();
                comm.Parameters.Add(paramid);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@nume";
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

                var paramcnp = comm.CreateParameter();
                paramcnp.ParameterName = "@cnp";
                paramcnp.Value = entity.Cnp;
                comm.Parameters.Add(paramcnp);

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

            log.InfoFormat("Entering update with value {}");
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE  Participant   set probe=@probe where cnp = @cnp";

                IDbDataParameter paramProbe = comm.CreateParameter();
                paramProbe.ParameterName = "@probe";
                string prob = "";
                foreach (Proba proba in entity.Proba)
                {
                    prob = proba.id + ",";
                }
                if (prob.Length > 2)
                {
                    prob = prob.Substring(0, prob.Length - 1);
                }
                paramProbe.Value = prob;
                comm.Parameters.Add(paramProbe);

                IDbDataParameter paramCnp = comm.CreateParameter();
                paramCnp.ParameterName = "@cnp";
                paramCnp.Value = entity.Cnp;
                comm.Parameters.Add(paramCnp);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    log.Info("nu s-a dat update");
                }
            }
            log.InfoFormat("Exiting update with value {0}", entity);
            return entity;
        }

        public int participantiprobe(string proba1)
        {
            int count = 0;
            IEnumerable<Participant> participants = FindAll();
            foreach (Participant p in participants)
            {
                foreach (Proba prob in p.Proba)
                {
                    if (prob.Proba1== proba1)
                    {
                        count += 1;
                    }
                }
            }
            return count;
        }

        public int participantiCategorie(int m, int M, string probaq)
        {
            int count = 0;
            IEnumerable<Participant> participants = participantlaProba(probaq);
            foreach (Participant p in participants)
            {
                if (p.Varsta >= m && p.Varsta <= M)
                {
                    count += 1;
                }
            }
            
            return count;
        }

        public IEnumerable<Participant> participantlaProba(string proba)
        {
            IEnumerable<Participant> participants = FindAll();
            List<Participant> participantiinscrisi=new List<Participant> ();
            foreach (Participant p in participants)
            {
                foreach (Proba prob in p.Proba)
                {
                    if (prob.Proba1 == proba)
                    {
                        participantiinscrisi.Add(p);
                    }
                }
            }
            return participantiinscrisi;
        }

        public IEnumerable<Participant> participantCategorie(int varstam, int varstaM, string proba)
        {
            IEnumerable<Participant> participants = participantlaProba(proba);
            List<Participant> participantiinscrisi = new List<Participant>();
            foreach (Participant p in participants)
            {
                if (p.Varsta >= varstam && p.Varsta <= varstaM)
                {
                    participantiinscrisi.Add(p);
                }
            }
            return participantiinscrisi;
        }

        public Participant searchwithCNP(string CNP)
        {
            IEnumerable<Participant> participants = FindAll();
            foreach (Participant p  in participants)
            {
                if (p.Cnp == CNP)
                {
                    return p;
                }
            }
            return null;
        }

       
    }
}
