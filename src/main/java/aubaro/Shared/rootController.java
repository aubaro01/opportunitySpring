package aubaro.Shared;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class rootController {

    @GetMapping("/")
    public Map<String, Object> root() {
        return Map.of(
                "name", "Opportunity API",
                "status", "UP"
        );
    }
}
