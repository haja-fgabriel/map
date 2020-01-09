using Laborator_9.Domain.Validators;
using Laborator_9.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Repository
{
    class MatchFileRepository : FileRepository<long, Match>
    {
        public MatchFileRepository(MatchValidator validator, String filename)
            : base(validator, filename, Parsers.ParseMatch, Unparsers.UnparseMatch)
        {

        }
    }
}
