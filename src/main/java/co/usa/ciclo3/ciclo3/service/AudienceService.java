package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Audience;
import co.usa.ciclo3.ciclo3.repository.AudienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceService {

    @Autowired
    private AudienceRepository audienceRepository;

    public List<Audience> getAll(){
        return audienceRepository.getAll();
    }

    public Optional<Audience> getAudience(int id){
        return audienceRepository.getAudience(id);
    }

    public Audience save(Audience p){
        if(p.getId()==null){
            return audienceRepository.save(p);
        }else{
            Optional<Audience> paux=audienceRepository.getAudience(p.getId());
            if(paux.isEmpty()){
                return audienceRepository.save(p);
            }else{
                return p;
            }
        }
    }

    public Audience update(Audience c){
        if(c.getId()!=null){
            Optional<Audience>g=audienceRepository.getAudience(c.getId());
            if(!g.isEmpty()){
                if(c.getName()!=null){
                    g.get().setName(c.getName());
                }
                if(c.getDescription()!=null){
                    g.get().setDescription(c.getDescription());
                }

                if(c.getCapacity()!=null){
                    g.get().setCapacity(c.getCapacity());
                }
                return audienceRepository.save(g.get());
            }
        }
        return c;

    }

    public boolean deleteCategory(int id){
        Optional<Audience> c=getAudience(id);
        if(!c.isEmpty()){
            audienceRepository.delete(c.get());
            return true;
        }
        return false;

    }

}
