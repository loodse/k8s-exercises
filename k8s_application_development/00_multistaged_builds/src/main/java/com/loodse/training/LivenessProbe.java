package com.loodse.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Endpoint(id="liveness")
@Component
public class LivenessProbe {

    private static final Logger LOG = LoggerFactory.getLogger(LivenessProbe.class);

    @ReadOperation
    public ResponseEntity<Health> liveness() {
        LOG.info("liveness request"); 
        if (MyController.HEALTHY) {
            return ResponseEntity.ok(Health.up().build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Health.down().build());
        }
    }

}