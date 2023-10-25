using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleApp1.domain;
using ConsoleApp1.repository;

namespace ConsoleApp1.repository0
{
    public interface IParticipant: IRepository<long,Participant>
    {
    }
}
