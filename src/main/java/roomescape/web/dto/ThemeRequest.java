package roomescape.web.dto;

import jakarta.validation.constraints.NotBlank;
import roomescape.domain.Theme;

public record ThemeRequest(@NotBlank String name, @NotBlank String description, @NotBlank String thumbnail) {
    public Theme toTheme() {
        return new Theme(name, description, thumbnail);
    }
}
