using Laborator_9.Domain;
using Laborator_9.Domain.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Repository
{


    class StudentFileRepository : FileRepository<long, Student>
    {
        public StudentFileRepository(StudentValidator validator, string filename)
            : base(validator, filename, Parsers.ParseStudent, Unparsers.UnparseStudent) {}

    }
}
