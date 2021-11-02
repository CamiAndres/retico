package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Audience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import co.usa.ciclo3.ciclo3.repository.crud.AudienceCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AudienceRepository {

    @Autowired
    private AudienceCrudRepository audienceCrudRepository;

    public List<Audience> getAll(){
        return (List<Audience>) audienceCrudRepository.findAll();
    }
    public Optional<Audience> getAudience(int id){
        return  audienceCrudRepository.findById(id);
    }

    public Audience save(Audience p){
        return audienceCrudRepository.save(p);
    }

    public void delete(Audience p){
        audienceCrudRepository.delete(p);
    }

}
