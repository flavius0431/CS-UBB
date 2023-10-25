using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleApp1.domain;

namespace ConsoleApp1.repository
{

    public interface IRepository<ID, E> where E : Entity<ID>
    {
        // Retrieves an entity by its ID
        // If the entity is found, it is returned, otherwise null is returned.
        // Throws an ArgumentException if the id parameter is null.
        E FindOne(ID id);

        // Retrieves all entities in the repository and returns them as an enumerable
        IEnumerable<E> FindAll();

        // Saves an entity to the repository
        // If the entity is successfully saved, null is returned
        // If the entity already exists, the method returns the entity
        // Throws a ValidationException if the entity is not valid
        // Throws an ArgumentException if the entity parameter is null
        E Save(E entity);

        // Deletes an entity by its ID
        // If the entity is found and deleted, the method returns the deleted entity
        // Otherwise, it returns null
        // Throws an ArgumentException if the id parameter is null
        E Delete(ID id);

        // Updates an existing entity in the repository
        // If the entity is updated successfully, null is returned
        // If the entity does not exist, the method returns the entity
        // Throws a ValidationException if the entity is not valid
        // Throws an ArgumentException if the entity parameter is null
        E Update(E entity);
    }
}
