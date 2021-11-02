/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Reservations;
import co.usa.ciclo3.ciclo3.repository.ReservationsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @autor: Camilo morales
 */
@Service
public class ReservationsService {
    @Autowired
    private ReservationsRepository metodosCrud;

   // Creación de la lista Reservations
    public List<Reservations> getAll(){
        return metodosCrud.getAll();
    }
// Optional de Reservations que permite trabajar con nulls
    public Optional<Reservations> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }
// Metodo guardar dada una reservación
    public Reservations save(Reservations reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservations> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
// Metodo actualizar dada una reservación 
    public Reservations update(Reservations reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservations> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
// Metodo que borra una reservación dado el ID
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
