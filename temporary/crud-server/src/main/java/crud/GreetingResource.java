package crud;

import crud.entity.Person;
import crud.repository.PersonRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/person")
public class GreetingResource {

    @Inject
    PersonRepository personRepository;
    

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String person() {
        Long testId = (long)1;
        Person person = personRepository.findById(testId);
        
        return person.getId() + " " + person.getName();
    }
}
