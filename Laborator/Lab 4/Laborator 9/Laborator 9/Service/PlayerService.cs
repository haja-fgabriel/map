using Laborator_9.Domain;
using Laborator_9.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Service
{
    class PlayerService
    {
        private PlayerFileRepository repository;
        private StudentFileRepository studentRepository;

        public PlayerService(PlayerFileRepository repository, StudentFileRepository studentRepository)
        {
            this.repository = repository;
            this.studentRepository = studentRepository;
        }

        public List<Player> GetPlayersByTeam(long teamID)
        {
            var query = from player in repository.FindAll()
                        join student in studentRepository.FindAll()
                            on player.ID equals student.ID
                        where player.Team == teamID
                        select new Player
                        {
                            ID = player.ID,
                            Name = student.Name,
                            School = student.School,
                            Team = player.Team
                        };
            return query.ToList();
        }

    }
}
