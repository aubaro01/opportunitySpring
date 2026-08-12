package aubaro.Shared.adapter.response;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class pageResponse implements Serializable {
    @Builder.Default
    private Integer totalPages = 0;
    @Builder.Default
    private Long totalElements = 0L;
    @Builder.Default
    private Integer currentPage = 0;
    @Builder.Default
    private Integer firstPage = 0;
    @Builder.Default
    private Integer nextPage = 0;
    @Builder.Default
    private Integer lastPage = 0;
    @Builder.Default
    private Integer previousPage = 0;

}
