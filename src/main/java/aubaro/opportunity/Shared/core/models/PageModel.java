package aubaro.opportunity.Shared.core.models;

import lombok.*;

import java.util.Collection;
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> {

    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer firstPage;
    private Integer nextPage;
    private Integer lastPage;
    private Integer previousPage;

    @Setter
    private Collection<T> page;
}
