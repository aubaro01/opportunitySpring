package aubaro.Shared.core.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserModel {

    private Long id;

    private String name;

    private String fullName;

    private String userLog;

    private String password;

    private Long clientId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
