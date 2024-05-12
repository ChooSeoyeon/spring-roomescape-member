package roomescape.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.ReservationRepository;
import roomescape.domain.Theme;
import roomescape.domain.ThemeRepository;
import roomescape.exception.theme.NotFoundThemeException;
import roomescape.exception.theme.ReservationReferencedThemeException;
import roomescape.service.dto.ThemeInput;
import roomescape.service.dto.ThemeOutput;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ReservationRepository reservationRepository;
    private final Clock clock;

    public ThemeService(ThemeRepository themeRepository, ReservationRepository reservationRepository, Clock clock) {
        this.themeRepository = themeRepository;
        this.reservationRepository = reservationRepository;
        this.clock = clock;
    }

    public List<ThemeOutput> findAllTheme() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeOutput::new)
                .toList();
    }

    public List<ThemeOutput> findAllPopularTheme() {
        String startDate = LocalDate.now(clock).minusDays(7L).toString();
        String endDate = LocalDate.now(clock).toString();
        List<Theme> themes = reservationRepository.findThemeWithMostPopularReservation(startDate, endDate);
        return themes.stream()
                .map(ThemeOutput::new)
                .toList();
    }

    public ThemeOutput saveTheme(ThemeInput request) {
        Theme theme = request.toTheme();
        Theme savedTheme = themeRepository.save(theme);
        return new ThemeOutput(savedTheme);
    }

    public void deleteTheme(long id) {
        Theme theme = findThemeById(id);
        if (reservationRepository.existsByThemeId(theme.getId())) {
            throw new ReservationReferencedThemeException();
        }
        themeRepository.delete(theme);
    }

    private Theme findThemeById(long id) {
        return themeRepository.findById(id)
                .orElseThrow(NotFoundThemeException::new);
    }
}
