package roomescape.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.service.ThemeService;
import roomescape.service.dto.ThemeInput;
import roomescape.service.dto.ThemeOutput;

@RequestMapping("/themes")
@RestController
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<List<ThemeOutput>> findAllTheme() {
        List<ThemeOutput> response = themeService.findAllTheme();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ThemeOutput>> findAllPopularTheme() {
        List<ThemeOutput> response = themeService.findAllPopularTheme();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ThemeOutput> saveTheme(@RequestBody ThemeInput request) {
        ThemeOutput response = themeService.saveTheme(request);
        return ResponseEntity.created(URI.create("/themes/" + response.getId())).body(response);
    }

    @DeleteMapping("/{themeId}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long themeId) {
        themeService.deleteTheme(themeId);
        return ResponseEntity.noContent().build();
    }
}
