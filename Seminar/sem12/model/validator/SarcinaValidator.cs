﻿using Curs12.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Curs12.Repository.Validator
{
    class SarcinaValidator : IValidator<Sarcina>
    {
        public void Validate(Sarcina e)
        {
            bool valid = true;
            if (valid == false)
            {
                throw new ValidationException("Obiectul nu e valid");
            }
        }
    }
}
