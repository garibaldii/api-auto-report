package com.matheus.api_auto_report.controller;

import com.matheus.api_auto_report.business.service.BussinessGroupService;
import com.matheus.api_auto_report.config.security.JwtTokenService;
import com.matheus.api_auto_report.config.security.JwtUserPayload;
import com.matheus.api_auto_report.infraestructure.dto.AuthenticatedUserDTO;
import com.matheus.api_auto_report.infraestructure.entity.BussinessGroupEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor  // do Lombok
public class BussinessGroupController {

    private final BussinessGroupService groupService;
    private final JwtTokenService jwtTokenService;


    @GetMapping("/{id}")
    public ResponseEntity<BussinessGroupEntity> getGroupById(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthenticatedUserDTO authUser
    ) {


        BussinessGroupEntity group = groupService.getGroupById(id, authUser.userId());

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<BussinessGroupEntity>> getGroups(@AuthenticationPrincipal AuthenticatedUserDTO authUser
    ) {

        List<BussinessGroupEntity> groups = groupService.getGroups(authUser.userId());

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BussinessGroupEntity> postGroup(
            @Valid @RequestBody BussinessGroupEntity entity,
            @AuthenticationPrincipal AuthenticatedUserDTO authUser
    ) {


        BussinessGroupEntity group = groupService.saveGroup(entity, authUser.userId());

        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BussinessGroupEntity> updateGroup(
            @PathVariable Long id,
            @RequestBody BussinessGroupEntity data,
            @AuthenticationPrincipal AuthenticatedUserDTO authUser
    ) {


        BussinessGroupEntity updated = groupService.updateGroup(id, data, authUser.userId());

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthenticatedUserDTO authUser

    ) {


        groupService.deleteGroup(id, authUser.userId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
