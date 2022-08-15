package com.karol.supervisorServerAndWorkers.workerClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HealthCheckClient {

    private final String masterAddress;

    public HealthCheckClient(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public Boolean healthCheck() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(masterAddress+"/actuator/health"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}
