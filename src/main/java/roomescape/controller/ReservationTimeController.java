package roomescape.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roomescape.service.ReservationTimeService;
import roomescape.service.dto.AvailableReservationTimeOutput;
import roomescape.service.dto.ReservationTimeInput;
import roomescape.service.dto.ReservationTimeOutput;

@RequestMapping("/times")
@RestController
public class ReservationTimeController {
    private final ReservationTimeService reservationTimeService;

    public ReservationTimeController(ReservationTimeService reservationTimeService) {
        this.reservationTimeService = reservationTimeService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationTimeOutput>> findAllReservationTime() {
        List<ReservationTimeOutput> response = reservationTimeService.findAllReservationTime();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableReservationTimeOutput>> findAllAvailableReservationTime(
            @RequestParam LocalDate date, @RequestParam(name = "theme-id") Long themeId) {
        List<AvailableReservationTimeOutput> response =
                reservationTimeService.findAllAvailableReservationTime(date, themeId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ReservationTimeOutput> saveReservationTime(@RequestBody ReservationTimeInput request) {
        ReservationTimeOutput response = reservationTimeService.saveReservationTime(request);
        return ResponseEntity.created(URI.create("/times/" + response.getId())).body(response);
    }

    @DeleteMapping("/{timeId}")
    public ResponseEntity<Void> deleteReservationTime(@PathVariable Long timeId) {
        reservationTimeService.deleteReservationTime(timeId);
        return ResponseEntity.noContent().build();
    }
}
