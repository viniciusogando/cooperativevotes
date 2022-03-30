package com.vinicius.cooperativevotes.controller.v1;

import com.vinicius.cooperativevotes.dto.VoteDto;
import com.vinicius.cooperativevotes.service.VoteService;
import org.junit.Test;
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
public class VoteControllerTest {

    @Mock
    public VoteService voteService;

    @InjectMocks
    public VoteController voteController;


    @Test
    public void createVoteTest() throws URISyntaxException {
        VoteDto voteDto = new VoteDto("1", "00000000000", true);

        Mockito.when(voteService.save(voteDto)).thenReturn(voteDto);

        VoteDto response = voteController.create(voteDto);

        assertEquals(response, voteDto);
    }

    @Test
    public void getVoteAllVotingSessionTest() {
        List<VoteDto> voteDtos = new ArrayList<>();
        VoteDto voteDto = new VoteDto("1", "00000000000", true);
        voteDtos.add(voteDto);

        Mockito.when(voteService.getAllVotingSession("1")).thenReturn(voteDtos);

        List<VoteDto> resp = voteController.getAllVotingSession("1");

        assertEquals(resp, voteDtos);
    }

    @Test
    public void getlVoteAllByCpfTest() {
        List<VoteDto> voteDtos = new ArrayList<>();
        VoteDto voteDto = new VoteDto("1", "00000000000", true);
        voteDtos.add(voteDto);

        Mockito.when(voteService.getAllByCpf("00000000000")).thenReturn(voteDtos);

        List<VoteDto> resp = voteController.getAllByCpf("00000000000");

        assertEquals(resp, voteDtos);
    }
}