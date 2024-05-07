package roomescape.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;
import roomescape.domain.Theme;

public record ReservationRequest(@NotNull LocalDate date,
                                 @NotBlank String name,
                                 @NotNull Long timeId,
                                 @NotNull Long themeId) {
    public Reservation toReservation(ReservationTime reservationTime, Theme theme) {
        return new Reservation(name, date, reservationTime, theme);
    }
}
