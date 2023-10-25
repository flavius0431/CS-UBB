using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaUI.domain;
using TemaUI.repository0;


namespace TemaUI.service
{
    public class Service
    {
        private IOficiuPersoana OpRepo;
        private IParticipant PRepo;
        private IProba PRepo2;

        public Service(IOficiuPersoana OpRepo, IParticipant PRepo, IProba PRepo2)
        {
            this.OpRepo = OpRepo;
            this.PRepo = PRepo;
            this.PRepo2 = PRepo2;
        }

        public OficiuPersoana Login(String user, String password)
        {
            return this.OpRepo.LogIn(user, password);
        }

        public IEnumerable<Participant> GetAllParticipants()
        {
            return this.PRepo.FindAll();
        }

        public Participant AddParticipant(Participant participant)
        {
            return this.PRepo.Save(participant);
        }

        public Participant UpdateParticipant(Participant participant)
        {
            return this.PRepo.Update(participant);
        }

        public Proba FindProba1(long id)
        {
            return this.PRepo2.FindOne(id);
        }

        public IEnumerable<Proba> FindAllProbs()
        {
            return this.PRepo2.FindAll();
        }

        public int ParticipantCategorieProba(int m, int M, String p)
        {
            return this.PRepo.participantiCategorie(m, M, p);
        }

        public IEnumerable<Participant> ParticipantiProba(String proba)
        {
            return this.PRepo.participantlaProba(proba);
        }

        public Participant SearchwithCNP(String CNP)
        {
            return this.PRepo.searchwithCNP(CNP);
        }

        public Tuple<int, int> GetNrCategorie(int varsta)
        {
            int varstamin = 0;
            int varstamax = 0;
            if (varsta >= 6 && varsta <= 8)
            {
                varstamin = 6;
                varstamax = 8;
            }
            if (varsta >= 9 && varsta <= 11)
            {
                varstamin = 9;
                varstamax = 11;
            }
            if (varsta >= 12 && varsta <= 15)
            {
                varstamin = 12;
                varstamax = 14;
            }
            Tuple<int, int> My_Tuple = new Tuple<int, int>(varstamin,varstamax);
            return My_Tuple;
        }

        public IEnumerable<Participant> FindProba(int varstamin, int varstamax, String proba)
        {
            IEnumerable<Participant> participants = this.PRepo.FindAll();
            List<Participant> probe = new List<Participant>();
            foreach (Participant p in participants)
            {
                if (p.Varsta >= varstamin && p.Varsta <= varstamax)
                {
                    foreach (Proba prob in p.Proba)
                    {
                        if (prob.Proba1 == proba)
                        {
                            probe.Add(p);
                        }
                    }
                }
            }

            return probe;
        }

        public IEnumerable<ProbaDTO> GetDTOProba()
        {
            List<ProbaDTO> probaDTOs= new List<ProbaDTO>();
            foreach( Proba proba in FindAllProbs())
            {
                int varstamin = proba.VarstaMin;
                int varstamax = proba.VarstaMax;
                String probastr = proba.Proba1;
               
                int nrParticipanti = ParticipantCategorieProba(varstamin, varstamax, probastr);
               
                long id = proba.id;
                ProbaDTO probaDTO=new ProbaDTO(varstamin,varstamax,probastr,nrParticipanti,id);
                probaDTOs.Add(probaDTO);
            }

            return probaDTOs;
           
        }




    }
}   
