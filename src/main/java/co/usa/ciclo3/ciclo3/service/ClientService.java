/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class ClientService {
       @Autowired
    private ClientRepository metodosCrud;
    
    public List<Client> getAll(){
        return metodosCrud.getAll();
    }
    
    
    //Para que la llave no se vaya en null
    public Optional<Client> getClient(int idClient){
        return metodosCrud.getClient(idClient);
    }
    
    public Client save(Client client){
        //si viene algo null lo guarda si no retorna audience
        if(client.getIdClient()==null){ 
            return metodosCrud.save(client);
        }else{
            Optional<Client> evt=metodosCrud.getClient(client.getIdClient());
            if(evt.isEmpty()){
                return metodosCrud.save(client);
            }else{
                return client;
            }
        }
        
        
    }
    
         public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= metodosCrud.getClient(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }
    
        public boolean deleteClient(int clientId) {
        Boolean aBoolean = getClient(clientId).map(client -> {
            metodosCrud.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
