using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_9.Domain;

namespace Laborator_9.Repository
{
    interface IRepository<ID, E> where E : Entity<ID>
    {

        /**
         * Parameters:
         *   id - the id of the entity to be returned
         *        must not be null
         *        
         * Returns:
         *   the entity with the specified id
         *   or null - if there is no entity with the given id
         *   
         * Throws:
         *   ArgumentNullException:
         *     if id is null.
         */
        E FindOne(ID id);

        /**
         * Returns:
         *   all entities
         */
        IEnumerable<E> FindAll();

        /**
         * Parameters: 
         *   entity entity must be not null
         *   
         * Returns: 
         *   null - if the given entity is saved
         *   otherwise returns the entity (id already exists)
         *   
         * Exceptions:
         *   ValidationException:
         *     if the entity is not valid
         *   ArgumentNullException:
         *     if the given entity is null.
         */
        E Save(E entity);


        /**
         * Summary:
         *   removes the entity with the specified id
         *
         * Parameters:
         *   ID id must be not null
         *   
         * Returns:
         *   the removed entity or null if there is no entity with the given id
         *   
         * Throws:
         *   ArgumentNullException:
         *     if the given id is null.
         */
        E Delete(ID id);

        /**
         * Parameters:
         *   entity entity must not be null
         *   
         * Returns: 
         *   null - if the entity is updated,
         *   otherwise  returns the entity  - (e.g id does not exist).
         *   
         * Throws: 
         *   ArgumentNullException:
         *     if the given entity is null.
         *   ValidationException:      
         *     if the entity is not valid.
         */
        E Update(E entity);
    }

}
