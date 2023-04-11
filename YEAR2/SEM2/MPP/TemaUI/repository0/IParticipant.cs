using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaUI.domain;
using TemaUI.repository0;

namespace TemaUI.repository0
{
    public interface IParticipant: IRepository<long,Participant>
    {
        public int participantiprobe(String proba1);
        public int participantiCategorie(int m, int M, String probaq);

        public IEnumerable<Participant> participantlaProba(String proba);

        public IEnumerable<Participant> participantCategorie(int varstam, int varstaM, String proba);

        public Participant searchwithCNP(String CNP);
    }
}
