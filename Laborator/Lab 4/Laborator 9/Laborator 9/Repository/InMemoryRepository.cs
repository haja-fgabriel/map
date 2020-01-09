using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_9.Repository;
using Laborator_9.Domain;
using Laborator_9.Domain.Validators;

namespace Laborator_9.Repository
{
    class InMemoryRepository<ID, E> : IRepository<ID, E> where E : Entity<ID>
    {
        protected IValidator<E> validator;
        protected Dictionary<ID, E> elements = new Dictionary<ID, E>();

        public InMemoryRepository(IValidator<E> validator)
        {
            this.validator = validator;
        }

        public virtual E Delete(ID id)
        {
            if (id == null)
                throw new ArgumentNullException("ID must not be null");
            if (!elements.ContainsKey(id))
                return default(E);
            E value = elements[id];
            elements.Remove(id);
            return value;
        }

        public IEnumerable<E> FindAll()
        {
            return elements.Values;
        }

        public E FindOne(ID id)
        {
            if (id == null)
                throw new ArgumentNullException("ID must not be null");
            return elements[id];
        }

        public virtual E Save(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException("entity must not be null");
            if (elements.ContainsKey(entity.ID))
                return entity;

            validator.Validate(entity);
            elements[entity.ID] = entity;
            return default(E);
        }

        public virtual E Update(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException("entity must not be null");
            if (!elements.ContainsKey(entity.ID))
                return entity;
            
            validator.Validate(entity);
            elements[entity.ID] = entity;
            return default(E);
        }
    }
}
