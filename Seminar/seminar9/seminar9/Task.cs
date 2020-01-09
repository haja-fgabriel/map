using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace seminar9
{
    abstract class Task
    {
        private string id;
        private string desc;

        public abstract void Execute();

        public string Id { get => id; set => id = value; }
        public string Desc { get => desc; set => desc = value; }
        

    }
}
