using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace seminar11
{
    internal abstract class Task
    {
        private string TaskID
        {

            get
            {
                return TaskID;
            }
            set
            {
                TaskID = value;
            }
        }

        private string Descriere
        {
            get
            {
                return Descriere;
            }
            set
            {
                Descriere = value;
            }
        }

        public Task(string taskID, string descriere) {
            TaskID = taskID;
            Descriere = descriere;

       
        }

        public abstract void execute();

        public override string ToString()
        {
            return base.ToString();
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public override bool Equals(object? obj)
        {
            return base.Equals(obj);
        }
    }
}
