package roomescape.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.ReservationRepository;
import roomescape.domain.ReservationTime;
import roomescape.domain.ReservationTimeRepository;
import roomescape.exception.time.DuplicatedTimeException;
import roomescape.exception.time.NotFoundTimeException;
import roomescape.exception.time.ReservationReferencedTimeException;
import roomescape.service.dto.AvailableReservationTimeOutput;
import roomescape.service.dto.ReservationTimeInput;
import roomescape.service.dto.ReservationTimeOutput;

@Service
public class ReservationTimeService {
    private final ReservationTimeRepository reservationTimeRepository;
    private final ReservationRepository reservationRepository;

    public ReservationTimeService(ReservationTimeRepository reservationTimeRepository,
                                  ReservationRepository reservationRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationTimeOutput> findAllReservationTime() {
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();
        return reservationTimes.stream()
                .map(ReservationTimeOutput::new)
                .toList();
    }

    public List<AvailableReservationTimeOutput> findAllAvailableReservationTime(LocalDate date, long themeId) {
        List<Long> bookedTimeIds = reservationRepository.findTimeIdByDateAndThemeId(date, themeId);
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();
        return reservationTimes.stream()
                .map(time -> toAvailableReservationTimeResponse(time, bookedTimeIds))
                .toList();
    }

    private AvailableReservationTimeOutput toAvailableReservationTimeResponse(
            ReservationTime time, List<Long> bookedTimeIds) {
        boolean alreadyBooked = time.isAlreadyBooked(bookedTimeIds);
        return new AvailableReservationTimeOutput(time, alreadyBooked);
    }

    public ReservationTimeOutput saveReservationTime(ReservationTimeInput request) {
        if (reservationTimeRepository.existsByStartAt(request.getStartAt())) {
            throw new DuplicatedTimeException();
        }
        ReservationTime reservationTime = request.toReservationTime();
        ReservationTime savedReservationTime = reservationTimeRepository.save(reservationTime);
        return new ReservationTimeOutput(savedReservationTime);
    }

    public void deleteReservationTime(long id) {
        ReservationTime reservationTime = findReservationTimeById(id);
        if (reservationRepository.existsByTimeId(reservationTime.getId())) {
            throw new ReservationReferencedTimeException();
        }
        reservationTimeRepository.delete(reservationTime);
    }

    private ReservationTime findReservationTimeById(long id) {
        return reservationTimeRepository.findById(id)
                .orElseThrow(NotFoundTimeException::new);
    }
}
