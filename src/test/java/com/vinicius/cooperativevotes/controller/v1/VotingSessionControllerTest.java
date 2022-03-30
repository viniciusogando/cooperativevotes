package com.vinicius.cooperativevotes.controller.v1;

import com.vinicius.cooperativevotes.dto.VotingSessionRequestDto;
import com.vinicius.cooperativevotes.dto.VotingSessionResponseDto;
import com.vinicius.cooperativevotes.service.VotingSessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionControllerTest {

    @Mock
    public VotingSessionService votingSessionService;

    @InjectMocks
    public VotingSessionController votingSessionController;


    @Test
    public void createVotingSessionTest() throws URISyntaxException {
        VotingSessionRequestDto votingSessionDto = new VotingSessionRequestDto("1", 2);

        VotingSessionResponseDto votingSession = new VotingSessionResponseDto("1", "1", 2, LocalDateTime.now(), "ABERTA", 0, 0);

        Mockito.when(votingSessionService.save(votingSessionDto)).thenReturn(votingSession);

        VotingSessionResponseDto response = votingSessionController.create(votingSessionDto);

        assertEquals(response, votingSession);
    }

    @Test
    public void getVotingSessionTest() {
        VotingSessionResponseDto votingSession = new VotingSessionResponseDto("1", "1", 2, LocalDateTime.now(), "ABERTA", 0, 0);

        Mockito.when(votingSessionService.get("1")).thenReturn(votingSession);

        VotingSessionResponseDto resp = votingSessionController.get("1");

        assertEquals(resp, votingSession);
    }

    @Test
    public void getAllVotingSessionTest() {
        List<VotingSessionResponseDto> votingSessions = new ArrayList<>();
        VotingSessionResponseDto votingSession = new VotingSessionResponseDto("1", "1", 2, LocalDateTime.now(), "ABERTA", 0, 0);
        votingSessions.add(votingSession);

        Mockito.when(votingSessionService.getAll()).thenReturn(votingSessions);

        List<VotingSessionResponseDto> resp = votingSessionController.getAll();

        assertEquals(resp, votingSessions);
    }
}