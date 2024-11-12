package com.SmartContracts.upc.smartcontract.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private String taskName;
    private String description;
    private String status;
    private LocalDate dueDate;
}
