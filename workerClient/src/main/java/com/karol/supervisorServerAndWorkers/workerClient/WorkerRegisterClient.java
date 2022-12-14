package com.karol.supervisorServerAndWorkers.workerClient;

import com.karol.supervisorServerAndWorkers.worker.api.VerificationApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WorkerRegisterClient implements VerificationApi {

    private final String masterAddress;

    public WorkerRegisterClient(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    @Override
    public Boolean verificationRegister(String uuid) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(masterAddress+"/verification/verification-register/"+uuid))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(response.statusCode());
            return Boolean.valueOf(response.body());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }
}
