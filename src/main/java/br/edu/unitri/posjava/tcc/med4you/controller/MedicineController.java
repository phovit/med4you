package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.model.Medicine;
import br.edu.unitri.posjava.tcc.med4you.service.MedicineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pauloho on 17/07/18.
 */
@RestController
@RequestMapping("medicine")
@Api(value = "Medicamentos", description = "API REST para controle de medicamentos", tags = { "Medicamentos" })
public class MedicineController {

    @Autowired
    private MedicineService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Busca por ID",
            notes ="Este endpoint é responsável por realizar a busca de um medicamento, por seu ID",
            response = Medicine.class)
    public Medicine findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Buscar Todos",
            notes ="Este endpoint é responsável realizar a busca de todos os medicamentos cadastrados na base",
            response = Medicine.class)
    public List<Medicine> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Exclusão por ID",
            notes ="Este endpoint é responsável por realizar a exclusão de um medicamento, por seu ID",
            response = Medicine.class)
    public void delete(Long id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Alterar",
            notes ="Este endpoint é responsável por realizar a alteração de um medicamento",
            response = Medicine.class)
    public void update(@RequestBody Medicine medicine) {
        service.update(medicine);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Salvar",
            notes ="Este endpoint é responsável por realizar a persistência de um medicamento",
            response = Medicine.class)
    public void save(@RequestBody Medicine medicine) {
        service.save(medicine);
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "Exclusão por ID",
            notes ="Este endpoint é responsável por realizar a exclusão de um medicamento, por seu nome ou trcho do nome",
            response = Medicine.class)
    public List<Medicine> findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

}
