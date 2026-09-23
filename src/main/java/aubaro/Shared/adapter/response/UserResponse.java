package aubaro.Shared.adapter.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private Long id;

    private String name;

    private String fullName;

    private String userLog;

    private String password;

    private Long clientId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
