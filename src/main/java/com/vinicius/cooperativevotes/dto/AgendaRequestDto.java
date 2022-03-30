package com.vinicius.cooperativevotes.dto;

import javax.validation.constraints.NotBlank;

public class AgendaRequestDto {
    @NotBlank(message = "Name é um campo obrigatório.")
    private String name;

    public AgendaRequestDto(){

    }

    public AgendaRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
