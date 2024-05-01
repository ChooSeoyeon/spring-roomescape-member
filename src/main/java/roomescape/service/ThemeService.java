package roomescape.service;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import roomescape.domain.Theme;
import roomescape.domain.ThemeRepository;
import roomescape.exception.theme.NotFoundThemeException;
import roomescape.exception.theme.ReservationReferencedThemeException;
import roomescape.web.dto.ThemeRequest;
import roomescape.web.dto.ThemeResponse;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<ThemeResponse> findAllTheme() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeResponse::from)
                .toList();
    }

    public ThemeResponse saveTheme(ThemeRequest request) {
        Theme theme = request.toTheme();
        Theme savedTheme = themeRepository.save(theme);
        return ThemeResponse.from(savedTheme);
    }

    public void deleteTheme(Long id) {
        Theme theme = findThemeById(id);
        try {
            themeRepository.delete(theme);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationReferencedThemeException();
        }
    }

    private Theme findThemeById(Long id) {
        return themeRepository.findById(id)
                .orElseThrow(NotFoundThemeException::new);
    }
}
