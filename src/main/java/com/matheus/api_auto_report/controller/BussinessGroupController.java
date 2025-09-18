package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.business.service.BussinessGroupService;
import com.matheus.api_auto_report.infraestructure.dto.UserResponseDTO;
import com.matheus.api_auto_report.infraestructure.entity.BussinessGroupEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor  // do Lombok
public class BussinessGroupController {

    private final BussinessGroupService service;

    @GetMapping("/{id}")
    public ResponseEntity<BussinessGroupEntity> getGroupById(@PathVariable Long id) {
        BussinessGroupEntity group = service.getGroupById(id);

        return ResponseEntity.ok(group);
    }

    @GetMapping("/")
    public ResponseEntity<List<BussinessGroupEntity>> getGroups(){
        List<BussinessGroupEntity> groups = service.getGroups();

        return ResponseEntity.ok(groups);
    }

    @PostMapping("/")
    public ResponseEntity<BussinessGroupEntity> postGroup(@Valid @RequestBody BussinessGroupEntity entity) {
        BussinessGroupEntity group = service.saveGroup(entity);

        URI uri = URI.create("/groups/" + group.getId());
        return ResponseEntity.created(uri).body(group);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BussinessGroupEntity> updateGroup(@PathVariable Long id, @RequestBody BussinessGroupEntity data) {
        BussinessGroupEntity updated = service.updateGroup(id, data);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        service.deleteGroup(id);
    }
}
