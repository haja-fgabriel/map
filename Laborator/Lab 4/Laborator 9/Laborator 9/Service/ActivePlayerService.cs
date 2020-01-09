using Laborator_9.Domain;
using Laborator_9.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Service
{
    class ActivePlayerService
    {
        private ActivePlayerFileRepository repository;
        private PlayerFileRepository playerRepository;

        public ActivePlayerService(ActivePlayerFileRepository repo, PlayerFileRepository playerRepo)
        {
            repository = repo;
            playerRepository = playerRepo;
        }

        public List<ActivePlayer> GetActivePlayers(long team, long match)
        {
            var q = from ap in repository.FindAll()
                    join p in playerRepository.FindAll() on ap.ID equals p.ID
                    where ap.MatchID == match && p.Team == team
                    select ap;
            return q.ToList();
        }
    }
}
