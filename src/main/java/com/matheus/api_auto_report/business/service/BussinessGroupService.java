package com.matheus.api_auto_report.business.service;

import com.matheus.api_auto_report.business.validator.BussinessGroupValidator;
import com.matheus.api_auto_report.exception.exs.NotFound;
import com.matheus.api_auto_report.infraestructure.entity.BussinessGroupEntity;
import com.matheus.api_auto_report.infraestructure.entity.EnterpriseEntity;
import com.matheus.api_auto_report.infraestructure.repositories.BussinessGroupRepository;
import com.matheus.api_auto_report.infraestructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BussinessGroupService {

    private final BussinessGroupRepository groupRepository;

    /* Estamos chamando o UserRepository para poder fazer a referência dos grupos com usuário sem a necessidade
     * de carregar o objeto inteiro do usuário(evitando a vulnerabilidade de vazamento de dados).*/
    private final UserRepository userRepository;
    private final BussinessGroupValidator validator;


    //cria um grupo vinculado ao usuário específico
    public BussinessGroupEntity saveGroup(BussinessGroupEntity entity, Long userId) {
        //getReferenceById faz somente a referencia ao objeto, nao carregando todos os dados do usuário
        entity.setUser(userRepository.getReferenceById(userId));
        return groupRepository.saveAndFlush(entity);
    }

    //acha todos os grupos do usuário específico
    public List<BussinessGroupEntity> getGroups(Long userId) {

        //encontra todos os grupos vinculados pelo id, funciona como se fosse essa query:
        //SELECT * FROM bussiness_groups bg WHERE bg.user_id = ?
        return groupRepository.findAllByUser_Id(userId);
    }

    //acha um grupo específico do usuário específico
    //SELECT FROM bussiness_groups bg WHERE bg.user_id = ? AND bg.id = ?
    public BussinessGroupEntity getGroupById(Long groupId, Long userId) {
        return groupRepository.findByIdAndUser_Id(groupId, userId)
                .orElseThrow(() -> new NotFound("Cant found related group"));
    }


    //UPDATE bussiness_groups
    //SET name = ?
    //WHERE id = ? AND user_id = ?;
    public BussinessGroupEntity updateGroup(Long id, BussinessGroupEntity data, Long userId) {
        validator.verifyGroupExistsById(id);

        BussinessGroupEntity entity = getGroupById(id, userId);

        if (data.getName() != null) entity.setName(data.getName());

        return groupRepository.saveAndFlush(entity);

    }

    public void deleteGroup(Long id, Long userId) {
        validator.verifyGroupExistsById(id);

        BussinessGroupEntity entity = getGroupById(id, userId);

        //faz o delete em cascata, ou seja. Possibilita a exclusao das empresas vinculadas a este grupo.
        //Seria interessante adicionar uma confirmação de código via e-mail para possibilitar a ação (crítica).
        groupRepository.delete(entity);

    }

    public List<EnterpriseEntity> getGroupEnterprises(Long groupId, Long userId) {
        validator.verifyGroupExistsById(groupId);

        BussinessGroupEntity group = getGroupById(groupId, userId);

        return group.getEnterprises();
    }


}
