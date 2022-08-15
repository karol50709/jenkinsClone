package com.karol.supervisorServerAndWorkers.masterClient;

import com.google.gson.Gson;
import com.karol.supervisorServerAndWorkers.beans.RegisterBean;
import com.karol.supervisorServerAndWorkers.master.api.RegisterApi;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MasterRegisterClient implements RegisterApi {

    private String masterAddress;

    private Gson gson;

    public MasterRegisterClient(String masterAddress) {
        this.masterAddress = masterAddress;
        this.gson = new Gson();
    }

    @Override
    public String register(RegisterBean registerBean) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(masterAddress+"/register"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(registerBean)))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    @Override
    public Boolean unregister(String workerAddress) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(masterAddress+"/unregister"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return Boolean.valueOf(response.body());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }
}
