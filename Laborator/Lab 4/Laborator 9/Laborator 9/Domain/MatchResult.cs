using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain
{
    class MatchResult
    {
        public long Match { get; set; }
        public long Team1 { get; set; }
        public long Team2 { get; set; }
        public int Score1 { get; set; }
        public int Score2 { get; set; }

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
            return "MatchResult {Match=" + Match.ToString() + ", Team1=" + Team1.ToString() + ", Score1=" 
                + Score1.ToString() + ", Team2=" + Team2.ToString() + ", Score2=" + Score2.ToString() + "}";
        }
    }
}
