package aubaro.Shared.core.models;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserModel {

    private Long id;

    private String name;

    private String userLog;

    private String password;

    private Long clientId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
