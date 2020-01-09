using Laborator_9.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Repository
{
    class Parsers
    {
        // TODO implement parser for Student
        public static Student ParseStudent(string line)
        {
            string[] fields = line.Split(',');
            return new Student
            {
                ID = long.Parse(fields[0]),
                Name = fields[1].Trim(),
                School = long.Parse(fields[2])
            };
        }

        public static Team ParseTeam(string line)
        {
            string[] fields = line.Split(',');
            return new Team
            {
                ID = long.Parse(fields[0]),
                Name = fields[1].Trim()
            };
        }

        public static Player ParsePlayer(string line)
        {
            string[] fields = line.Split(',');
            return new Player
            {
                ID = long.Parse(fields[0]),
                Team = long.Parse(fields[1])
            };
        }

        public static ActivePlayer ParseActivePlayer(string line)
        {
            string[] fields = line.Split(',');
            return new ActivePlayer
            {
                ID = long.Parse(fields[0]),
                MatchID = long.Parse(fields[1]),
                ScoredPoints = int.Parse(fields[2]),
                Type = (PlayerType)Enum.Parse(typeof(PlayerType), fields[3])
            };
        }

        public static Match ParseMatch(string line)
        {
            string[] fields = line.Split(',');
            return new Match
            {
                ID = long.Parse(fields[0]),
                Team1 = long.Parse(fields[1]),
                Team2 = long.Parse(fields[2]),
                Date = DateTime.Parse(fields[3])
            };
        }
    }
}
