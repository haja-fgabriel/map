using Laborator_9.Domain;
using Laborator_9.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Service
{
    class MatchService
    {
        private MatchFileRepository repository;
        private ActivePlayerFileRepository activePlayerRepository;
        private PlayerFileRepository playerRepository;

        public MatchService(MatchFileRepository repo, ActivePlayerFileRepository activePlayerRepo, PlayerFileRepository playerRepo)
        {
            repository = repo;
            activePlayerRepository = activePlayerRepo;
            playerRepository = playerRepo;
        }

        public List<Match> GetMatches(DateTime begin, DateTime end)
        {
            return repository.FindAll()
                .Where(match => DateTime.Compare(begin, match.Date) <= 0 && DateTime.Compare(match.Date, end) <= 0)
                .ToList();
        }

        private int GetScoreForTeam(long match, long team)
        {
            var res1 = from activePlayer in activePlayerRepository.FindAll()
                       join player in playerRepository.FindAll() on activePlayer.ID equals player.ID
                       where activePlayer.MatchID == match && player.Team == team
                       group activePlayer by player.Team into gp
                       select new { MatchID = 0, Score = gp.Sum(p => p.ScoredPoints) };
            
            return res1.ToList()[0].Score;
        }

        public MatchResult GetMatchResult(long match)
        {
            Match m = repository.FindOne(match);
            if (m == null)
            {
                return default(MatchResult);
            }

            return new MatchResult
            {
                Match = match,
                Team1 = m.Team1,
                Team2 = m.Team2,
                Score1 = GetScoreForTeam(match, m.Team1),
                Score2 = GetScoreForTeam(match, m.Team2)
            };
        }
    }

}
