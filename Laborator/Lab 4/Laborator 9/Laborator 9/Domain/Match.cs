using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain
{
    class Match : Entity<long>
    {
        public long Team1 { get; set; }
        public long Team2 { get; set; }
        public DateTime Date { get; set; }

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
            return "Match {ID=" + ID.ToString() + ", Team1=" + Team1.ToString() + ", Team2=" 
                + Team2.ToString() + ", Date=" + Date.ToString("yyyy.MM.dd HH:mm") + "}";
        }
    }
}
