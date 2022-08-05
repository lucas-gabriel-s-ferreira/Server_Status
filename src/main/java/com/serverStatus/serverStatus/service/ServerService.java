package com.serverStatus.serverStatus.service;

import com.serverStatus.serverStatus.model.Server;
import com.serverStatus.serverStatus.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import static com.serverStatus.serverStatus.model.enumeration.Status.SERVER_DOWN;
import static com.serverStatus.serverStatus.model.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
public class ServerService {

    private final ServerRepository serverRepository;

    public Server create(Server server) {
        return serverRepository.save(server);
    }

    public Server ping(String ipAddress) throws IOException {
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    public Collection<Server> list(int limit) {
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    public Server findById(Long id) {
        return serverRepository.findById(id).get();
    }

    public Boolean delete(Long id) {
        serverRepository.deleteById(id);
        return TRUE;
    }

}
