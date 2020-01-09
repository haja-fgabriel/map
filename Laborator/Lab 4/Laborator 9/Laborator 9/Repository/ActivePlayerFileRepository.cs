using Laborator_9.Domain;
using Laborator_9.Domain.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Repository
{
    class ActivePlayerFileRepository : FileRepository<long, ActivePlayer>
    {
        public ActivePlayerFileRepository(ActivePlayerValidator validator, String filename)
            : base(validator, filename, Parsers.ParseActivePlayer, Unparsers.UnparseActivePlayer) {}
    }
}
