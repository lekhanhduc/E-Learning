package vn.khanhduc.elearning.dto.response;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private int currentPage;
    private int size;
    private int totalPages;
    private long totalElements;

    @Builder.Default
    private List<T> data = Collections.emptyList();
}
