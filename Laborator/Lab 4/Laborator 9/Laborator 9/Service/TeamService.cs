using Laborator_9.Domain;
using Laborator_9.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Service
{
    class TeamService
    {
        private TeamFileRepository repository;
        private PlayerFileRepository playerRepository;
        private StudentFileRepository studentRepository;

        public TeamService(TeamFileRepository repo, PlayerFileRepository playerRepo, StudentFileRepository studentRepo)
        {
            repository = repo;
            playerRepository = playerRepo;
            studentRepository = studentRepo;
        }

        
    }
}
