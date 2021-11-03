/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.Reports.CountClient;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Reservations;
import co.usa.ciclo3.ciclo3.repository.crud.ReservationsCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public class ReservationsRepository {
    @Autowired
    private ReservationsCrudRepository crud4;

    public List<Reservations> getAll(){
        return (List<Reservations>) crud4.findAll();
    }
    public Optional<Reservations> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservations save(Reservations reservation){
        return crud4.save(reservation);
    }
    public void delete(Reservations reservation){
        crud4.delete(reservation);
    }
    public List<Reservations> ReservationStatus (String status){
         return crud4.findAllByStatus(status);
     }
     
     public List<Reservations> ReservationTime (Date a, Date b){
         return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
     }
   
     public List<CountClient> getTopClient(){
         List<CountClient> res=new ArrayList<>();
         List<Object[]>report = crud4.countTotalReservationsByClient();
         for(int i=0; i<report.size();i++){
             res.add(new CountClient((Long)report.get(i)[1],(Client) report.get(i)[0]));
         
         }
         return res;
     }
}
