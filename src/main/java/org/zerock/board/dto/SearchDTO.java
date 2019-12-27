package org.zerock.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDTO {

    private SearchTypeEnum type;
    private String keyword;
}
