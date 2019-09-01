package com.waes.diffeder.diffeder.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiffResponse {
    private DiffEqualsResult diffResult;
    private String difference;
}
