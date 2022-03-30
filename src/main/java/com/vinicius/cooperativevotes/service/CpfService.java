package com.vinicius.cooperativevotes.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CpfService {
    public String cpfAbleToVote(String cpf) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create("https://user-info.herokuapp.com/users/" + cpf))
                .header("accept", "application/json")
                .build();

            HttpResponse<String> result = client.send(request, HttpResponse.BodyHandlers.ofString());

            String msgUnableToVote = "O CPF {" + cpf + "} é inválido";

            return result.body().contains("ABLE_TO_VOTE") ? "" : msgUnableToVote;
        } catch (InterruptedException | IOException ex) {
            return "Não foi possível consultar o cpf {" + cpf + "}";
        }
    }

}
