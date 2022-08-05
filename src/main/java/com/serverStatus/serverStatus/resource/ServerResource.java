package com.serverStatus.serverStatus.resource;

import com.serverStatus.serverStatus.model.Server;
import com.serverStatus.serverStatus.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server")
public class ServerResource {
    private final ServerService serverService;

    @GetMapping("/list")
    public ResponseEntity<Collection<Server>> findAllServer(){
        Collection<Server> list = serverService.list(10);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Server> getServerById(@PathVariable("id") Long id){
        Server server = serverService.findById(id);
        return ResponseEntity.ok().body(server);
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Server> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok().body(server);
    }

    @PostMapping("/add")
    public ResponseEntity<Server> addServer(@RequestBody @Valid Server server) {
        Server obj = serverService.create(server);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Server> deleteServer(@PathVariable("id") Long id) {
        serverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
