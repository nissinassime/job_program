package xyz.moyyn.program.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import xyz.moyyn.program.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDataRepository extends JpaRepository<Person, Long> {


     List<Person> findAll() ;

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param person must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException          in case the given {@literal person} is {@literal null}.
     */
   Person save(@NonNull Person person);

    /**
     * Retrieves an entity by its id.
     *
     * @param personId must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
     Optional<Person> findById(@NonNull Long personId) ;

    /**
     * Deletes the entity with the given id.
     * <p>
     * If the entity is not found in the persistence store it is silently ignored.
     *
     * @param personId must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     */
     void deleteById(Long personId);
}
