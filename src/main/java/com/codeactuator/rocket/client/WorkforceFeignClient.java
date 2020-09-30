package com.codeactuator.rocket.client;

import com.codeactuator.rocket.domain.Workforce;
import com.codeactuator.rocket.dto.WorkforceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "WORKFORCE-SERVICE")
public interface WorkforceFeignClient {


    @GetMapping("/v1/workforces/{id}")
    public WorkforceDTO findById(@PathVariable("id") Long id);
}
