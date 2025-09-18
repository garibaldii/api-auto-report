package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.business.service.BussinessGroupService;
import com.matheus.api_auto_report.config.security.JwtTokenService;
import com.matheus.api_auto_report.config.security.UserDetailsImpl;
import com.matheus.api_auto_report.infraestructure.entity.BussinessGroupEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor  // do Lombok
public class BussinessGroupController {

    private final BussinessGroupService service;
    private final JwtTokenService jwtTokenService;

    @GetMapping("/{id}")
    public ResponseEntity<BussinessGroupEntity> getGroupById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        BussinessGroupEntity group = service.getGroupById(id, );

        return ResponseEntity.ok(group);
    }

    @GetMapping("/")
    public ResponseEntity<List<BussinessGroupEntity>> getGroups(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<BussinessGroupEntity> groups = service.getGroups(userDetails.getUser());

        return ResponseEntity.ok(groups);
    }

    @PostMapping("/")
    public ResponseEntity<BussinessGroupEntity> postGroup(
            @Valid @RequestBody BussinessGroupEntity entity,
            UserDetailsImpl userDetails) {
        BussinessGroupEntity group = service.saveGroup(entity, userDetails.getUser());

        URI uri = URI.create("/groups/" + group.getId());
        return ResponseEntity.created(uri).body(group);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BussinessGroupEntity> updateGroup(
            @PathVariable Long id,
            @RequestBody BussinessGroupEntity data,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        BussinessGroupEntity updated = service.updateGroup(id, data, userDetails.getUser());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        service.deleteGroup(id, userDetails.getUser());
    }
}
