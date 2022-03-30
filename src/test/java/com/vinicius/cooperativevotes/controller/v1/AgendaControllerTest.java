package com.vinicius.cooperativevotes.controller.v1;

import com.vinicius.cooperativevotes.dto.AgendaRequestDto;
import com.vinicius.cooperativevotes.dto.AgendaResponseDto;
import com.vinicius.cooperativevotes.model.Agenda;
import com.vinicius.cooperativevotes.service.AgendaService;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AgendaControllerTest {

    @Mock
    public AgendaService agendaService;

    @InjectMocks
    public AgendaController agendaController;


    @Test
    public void createAgendaTest() throws URISyntaxException {
        AgendaRequestDto agendaDto = new AgendaRequestDto("agenda");

        Agenda agenda = new Agenda("agenda");
        agenda.setId("1");

        Mockito.when(agendaService.save(agendaDto)).thenReturn(agenda);

        Agenda response = agendaController.create(agendaDto);

        assertEquals(response, agenda);
    }

    @Test
    public void getAgendaTest() {
        AgendaResponseDto agenda = new AgendaResponseDto("1", "agenda", "1", "ABERTA", 0, 0);
 
        Mockito.when(agendaService.get("1")).thenReturn(agenda);

        AgendaResponseDto resp = agendaController.get("1");

        assertEquals(resp, agenda);
    }

    @Test
    public void getAllAgendaTest() {
        List<Agenda> agendas = new ArrayList<>();
        Agenda agenda = new Agenda("agenda");
        agenda.setId("1");
        agendas.add(agenda);

        Mockito.when(agendaService.getAll()).thenReturn(agendas);

        List<Agenda> resp = agendaController.getAll();

        assertEquals(resp, agendas);
    }
}