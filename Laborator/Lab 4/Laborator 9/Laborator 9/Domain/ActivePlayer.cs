using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain
{
    enum PlayerType : byte
    {
        Backup,
        Participant
    }

    class ActivePlayer : Entity<long>
    {
        public long MatchID { get; set; }
        public int ScoredPoints { get; set; }
        public PlayerType Type { get; set; }

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
            return "ActivePlayer {ID=" + ID.ToString() + ", MatchID=" + MatchID.ToString() + ", ScoredPoints="
                + ScoredPoints.ToString() + ", Type=" + Type.ToString() + "}";
        }
    }
}
