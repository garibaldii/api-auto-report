package com.matheus.api_auto_report.business.service;

import com.matheus.api_auto_report.business.validator.BussinessGroupValidator;
import com.matheus.api_auto_report.exception.exs.NotFound;
import com.matheus.api_auto_report.infraestructure.entity.BussinessGroupEntity;
import com.matheus.api_auto_report.infraestructure.entity.EnterpriseEntity;
import com.matheus.api_auto_report.infraestructure.entity.UserEntity;
import com.matheus.api_auto_report.infraestructure.repositories.BussinessGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BussinessGroupService {

    private final UserEntity user;
    private final BussinessGroupRepository repository;
    private final BussinessGroupValidator validator;

    public BussinessGroupEntity saveGroup(BussinessGroupEntity entity) {
        return repository.saveAndFlush(entity);
    }

    public List<BussinessGroupEntity> getGroups() {
        return user.getGroups();
    }

    public BussinessGroupEntity getGroupById(Long id) {
        return user.getGroups().stream().filter(
                group -> group.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFound("Cant found  group id vinculated with your user"));
    }

    public BussinessGroupEntity updateGroup(Long id, BussinessGroupEntity data) {
        validator.verifyGroupExistsById(id);

        BussinessGroupEntity entity = getGroupById(id);

        if(data.getName() != null) entity.setName(data.getName());

        repository.save(entity);

        return entity;
    }

    public void deleteGroup(Long id) {
        validator.verifyGroupExistsById(id);

        BussinessGroupEntity entity = getGroupById(id);

        //faz o delete em cascata, ou seja. Possibilita a exclusao das empresas vinculadas a este grupo.
        //Seria interessante adicionar uma confirmação de código via e-mail para possibilitar a ação (crítica).
        repository.deleteById(id);

    }


}
