﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lucrare.Repository
{
    interface IRepository<T>
    {
        ICollection<T> FindAll();
    }
}
