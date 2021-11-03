/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.Reports.CountClient;
import co.usa.ciclo3.ciclo3.Reports.StatusReservations;
import co.usa.ciclo3.ciclo3.model.Reservations;
import co.usa.ciclo3.ciclo3.service.ReservationsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class ReservationsController {
    @Autowired
    private ReservationsService servicio;
    @GetMapping("/all")
    public List<Reservations> getReservations(){
        return servicio.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Reservations> getReservation(@PathVariable("id") int reservationId) {
        return servicio.getReservation(reservationId);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations save(@RequestBody Reservations reservation) {
        return servicio.save(reservation);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations update(@RequestBody Reservations reservation) {
        return servicio.update(reservation);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return servicio.deleteReservation(reservationId);
    }
        @GetMapping("/report-status")
    public StatusReservations getReservas(){
        return servicio.getReporteStatusReservations();
    }
    
        @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservations> getReservasTiempo (@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo")String dateTwo){
        return servicio.getReportesTiempoReservaciones(dateOne, dateTwo);
    }
    
    @GetMapping("/report-clients")
    public List<CountClient> getClientes(){
        return servicio.servicioTopClientes();
    
    }
}
