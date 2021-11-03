/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.Reports.CountClient;
import co.usa.ciclo3.ciclo3.Reports.StatusReservations;
import co.usa.ciclo3.ciclo3.model.Reservations;
import co.usa.ciclo3.ciclo3.repository.ReservationsRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ReservationsRepository ReservationCrudRepository;

   // Creación de la lista Reservations
    public List<Reservations> getAll(){
        return ReservationCrudRepository.getAll();
    }
// Optional de Reservations que permite trabajar con nulls
    public Optional<Reservations> getReservation(int reservationId) {
        return ReservationCrudRepository.getReservation(reservationId);
    }
// Metodo guardar dada una reservación
    public Reservations save(Reservations reservation){
        if(reservation.getIdReservation()==null){
            return ReservationCrudRepository.save(reservation);
        }else{
            Optional<Reservations> e= ReservationCrudRepository.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return ReservationCrudRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }
// Metodo actualizar dada una reservación 
    public Reservations update(Reservations reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservations> e= ReservationCrudRepository.getReservation(reservation.getIdReservation());
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
                ReservationCrudRepository.save(e.get());
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
            ReservationCrudRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    public StatusReservations getReporteStatusReservations(){
        List<Reservations>completed= ReservationCrudRepository.ReservationStatus("completed");
        List<Reservations>cancelled= ReservationCrudRepository.ReservationStatus("cancelled");
        return new StatusReservations(completed.size(), cancelled.size());
    }
    
    public List<Reservations> getReportesTiempoReservaciones(String datoA, String datoB){
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return ReservationCrudRepository.ReservationTime(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }  
    
    public List<CountClient> servicioTopClientes(){
        return ReservationCrudRepository.getTopClient();
    }
    
}
