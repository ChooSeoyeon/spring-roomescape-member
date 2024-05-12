package roomescape.service.dto;

import java.time.DateTimeException;
import java.time.LocalDate;
import roomescape.controller.dto.AdminReservationRequest;
import roomescape.controller.dto.ReservationRequest;
import roomescape.domain.Member;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;
import roomescape.domain.Theme;

public class ReservationInput {
    private final LocalDate date;
    private final Long timeId;
    private final Long themeId;

    public ReservationInput(String date, String timeId, String themeId) {
        validate(date, timeId, themeId);
        this.date = LocalDate.parse(date);
        this.timeId = Long.parseLong(timeId);
        this.themeId = Long.parseLong(themeId);
    }

    public ReservationInput(ReservationRequest request) {
        this.date = request.getDate();
        this.timeId = request.getTimeId();
        this.themeId = request.getThemeId();
    }

    public ReservationInput(AdminReservationRequest request) {
        this.date = request.getDate();
        this.timeId = request.getTimeId();
        this.themeId = request.getThemeId();
    }

    public void validate(String date, String timeId, String themeId) {
        if (date == null || timeId == null || themeId == null) {
            throw new IllegalArgumentException();
        }
        try {
            LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException();
        }
    }

    public Reservation toReservation(Member member, ReservationTime reservationTime, Theme theme) {
        return new Reservation(member.getName(), date, reservationTime, theme);
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getTimeId() {
        return timeId;
    }

    public Long getThemeId() {
        return themeId;
    }
}
