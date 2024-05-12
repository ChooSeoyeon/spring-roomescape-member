package roomescape.controller.dto;

import java.time.LocalDate;
import roomescape.domain.Reservation;
import roomescape.service.dto.ReservationOutput;

public class ReservationResponse {
    private final Long id;
    private final String name;
    private final LocalDate date;
    private final ReservationTimeResponse time;
    private final ThemeResponse theme;

    public ReservationResponse(
            Long id, String name, LocalDate date, ReservationTimeResponse time, ThemeResponse theme) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.theme = theme;
    }

    public ReservationResponse(Reservation reservation) {
        this(reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                new ReservationTimeResponse(reservation.getTime()),
                new ThemeResponse(reservation.getTheme())
        );
    }

    public ReservationResponse(ReservationOutput output) {
        this(output.getId(),
                output.getName(),
                output.getDate(),
                new ReservationTimeResponse(output.getTime()),
                new ThemeResponse(output.getTheme())
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

    public ReservationTimeResponse getTime() {
        return time;
    }

    public ThemeResponse getTheme() {
        return theme;
    }
}
