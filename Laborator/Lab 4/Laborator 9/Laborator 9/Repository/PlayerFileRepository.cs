using Laborator_9.Domain;
using Laborator_9.Domain.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Repository
{
    class PlayerFileRepository : FileRepository<long, Player>
    {
        public PlayerFileRepository(PlayerValidator validator, string filename)
            : base(validator, filename, Parsers.ParsePlayer, Unparsers.UnparsePlayer)
        {

        }
    }
}
