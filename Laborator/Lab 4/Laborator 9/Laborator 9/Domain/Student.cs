using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Domain
{
    class Student : Entity<long>
    {
        public string Name { get; set; }
        public long School { get; set; }

        public override bool Equals(object obj)
        {
            if (this == obj)
                return true;
            if (!(obj is Student))
                return false;
            Student student = (Student)obj;
            return ID == student.ID;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public override string ToString()
        {
            return "Student {ID=" + ID.ToString() + ", Name=" + Name + ", School=" + School.ToString() + "}";
        }
    }
}
