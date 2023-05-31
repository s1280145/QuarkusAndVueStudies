package com.example.api;

import com.example.entity.Reservation;
import com.example.repository.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ReservationApi {
    @Inject
    ReservationRepository reservationRepository;

    @GET
    @Path("{id}")
    public Reservation getReservation(@PathParam("id") Long id) {
        return reservationRepository.findById(id);
    }

    @GET
    public List<Reservation> getReservationList() {
        return reservationRepository.listAll();
    }

    @POST
    public void createReservation(Reservation reservation) {
        reservationRepository.persist(reservation);
    }

    @PUT
    @Path("{id}")
    public void updateReservation(@PathParam("id") Long id, Reservation reservation) {
        Reservation updateReservation = reservationRepository.findById(id);
        updateReservation.setUserName(reservation.getUserName());
        updateReservation.setDate(reservation.getDate());
        updateReservation.setStartTime(reservation.getStartTime());
        updateReservation.setEndTime(reservation.getEndTime());
        updateReservation.setPurpose(reservation.getPurpose());
        reservationRepository.persist(updateReservation);
    }

    @DELETE
    @Path("{id}")
    public void deleteReservation(@PathParam("id") Long id) {
        reservationRepository.deleteById(id);
    }
}
