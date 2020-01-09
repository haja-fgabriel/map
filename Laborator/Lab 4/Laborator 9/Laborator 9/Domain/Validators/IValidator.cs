using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain.Validators
{
    interface IValidator<E>
    {
        void Validate(E e);
    }
}
