package roomescape.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record ReservationTimeRequest(@JsonFormat(shape = Shape.STRING, pattern = "HH:mm") LocalTime startAt) {
    public ReservationTime toReservationTime() {
        return new ReservationTime(startAt);
    }
}
