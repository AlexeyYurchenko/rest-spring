package com.example.rest.rest.web.controller.v2;

import com.example.rest.rest.mapper.v2.ClientMapperV2;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.web.model.ClientListResponse;
import com.example.rest.rest.web.model.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/client")
@RequiredArgsConstructor
public class ClientControllerV2 {

    private final ClientService databaseClientService;
    private final ClientMapperV2 clientMapper;

    @GetMapping
    public ResponseEntity<ClientListResponse> findAll(){
        return ResponseEntity
                .ok(clientMapper.clientListToClientResponseList(databaseClientService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientMapper.clientToResponse(databaseClientService.findById(id)));
    }

}
