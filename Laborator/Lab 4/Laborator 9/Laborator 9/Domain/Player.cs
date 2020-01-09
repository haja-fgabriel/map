using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain
{
    class Player : Student
    {
        public long Team { get; set; }

        public override bool Equals(object obj)
        {
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public override string ToString()
        {
            return "Team {ID=" + ID.ToString() + ", Name=" + Name + ", Team=" + Team.ToString() + "}";
        }
    }
}
