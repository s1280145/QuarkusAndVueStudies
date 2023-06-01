package com.example.service;

import com.example.entity.Reservation;
import com.example.repository.ReservationRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReservationService {
    @Inject
    ReservationRepository reservationRepository;

    public void createReservation(Reservation reservation) {
        boolean success = false;
        success = judgeCreateReservation(reservation);
        if(success) {
            reservationRepository.persist(reservation);
        }
        else return;
    }

    private boolean judgeCreateReservation(Reservation reservation) {
        List<Reservation> reservations = reservationRepository.findByDate(reservation.getDate());
        boolean notOverlap = true;
        LocalTime startTime = reservation.getStartTime();
        LocalTime endTime = reservation.getEndTime();
        for(int i = 0; i < reservations.size(); i++) {
            LocalTime existStartTime = reservations.get(i).getStartTime();
            LocalTime existEndTime = reservations.get(i).getEndTime();
            if(startTime.isAfter(existStartTime) && startTime.isBefore(existEndTime)){
                notOverlap = false;
                break;
            }
            if(endTime.isAfter(existStartTime) && endTime.isBefore(existEndTime)){
                notOverlap = false;
                break;
            }
        }
        return notOverlap;
    }

    public List<Reservation> createReservationListByMonth() {
        LocalDate nowDate = LocalDate.now();
        int nowMonth = nowDate.getMonthValue();
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> allReservations = reservationRepository.listAll(Sort.by("date").and("startTime"));
        List<Reservation> reservations = new ArrayList<>();
        for(int i = 0; i < allReservations.size(); i++) {
            Reservation referenceReservation = allReservations.get(i);
            LocalDateTime dateTime = LocalDateTime.of(referenceReservation.getDate(),referenceReservation.getEndTime());
            if(dateTime.isAfter(now)) {
                int month = allReservations.get(i).getDate().getMonthValue();
                if (nowMonth == month) {
                    Reservation reservation = checkUsageSituation(referenceReservation);
                    reservations.add(reservation);
                }
            }
        }
        return  reservations;
    }

    private Reservation checkUsageSituation(Reservation reservation) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        if(nowDate.getDayOfMonth() == reservation.getDate().getDayOfMonth()) {
            if (nowTime.isAfter(reservation.getStartTime()) && nowTime.isBefore(reservation.getEndTime())) {
                reservation.setUsageSituation(true);
            } else reservation.setUsageSituation(false);
        }
        updateUsageSituation(reservation);
        return reservation;
    }

    private void updateUsageSituation(Reservation reservation) {
        Reservation updateReservation = reservationRepository.findById(reservation.getId());
        updateReservation.setUsageSituation(reservation.getUsageSituation());
        reservationRepository.persist(updateReservation);
    }
}
