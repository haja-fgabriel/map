using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain
{

    class Team : Entity<long>
    {
        public string Name { get; set; }

        public override string ToString()
        {
            return "Team {ID=" + ID.ToString() + ", Name=" + Name + "}";
        }
    }

}
