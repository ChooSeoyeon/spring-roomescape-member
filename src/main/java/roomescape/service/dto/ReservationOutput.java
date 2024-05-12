package roomescape.service.dto;

import java.time.LocalDate;
import roomescape.domain.Reservation;

public class ReservationOutput {
    private final Long id;
    private final String name;
    private final LocalDate date;
    private final ReservationTimeOutput time;
    private final ThemeOutput theme;

    public ReservationOutput(
            Long id, String name, LocalDate date, ReservationTimeOutput time, ThemeOutput theme) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.theme = theme;
    }

    public ReservationOutput(Reservation reservation) {
        this(reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                new ReservationTimeOutput(reservation.getTime()),
                new ThemeOutput(reservation.getTheme())
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReservationTimeOutput getTime() {
        return time;
    }

    public ThemeOutput getTheme() {
        return theme;
    }
}
