package com.serverStatus.serverStatus.repository;

import com.serverStatus.serverStatus.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {

    Server findByIpAddress(String ipAdress);

}
