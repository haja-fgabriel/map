using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain.Validators
{
    class StudentValidator : IValidator<Student>
    {
        public void Validate(Student e)
        {
            string errors = "";
            if (e.ID < 0)
                errors += "Invalid ID ";
            if (e.Name == "")
                errors += "Invalid name ";
            if (e.School < 0)
                errors += "Invalid school ID ";

            if (errors.Length > 0)
            {
                Console.WriteLine(errors);
                throw new ValidationException(errors);
            }
        }
    }
}
