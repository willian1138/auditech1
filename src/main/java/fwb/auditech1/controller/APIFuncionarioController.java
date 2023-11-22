package fwb.auditech1.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwb.auditech1.model.Funcionario;
import fwb.auditech1.service.IFuncionarioService;

@RestController
@RequestMapping("/api/v1/recepcionistas")
public class APIFuncionarioController {
 
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IFuncionarioService funcionarioServico;
    
    @CrossOrigin
    @GetMapping
    
    public ResponseEntity <Object> consultaFuncionarios(){
        logger.info(">>>>>> apicontroller consulta todos os funcionarios");
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioServico.consultaRegistroFuncionarios());
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3000/"})
    @GetMapping("/{id}")
    public ResponseEntity <Object> consultaPorId(@PathVariable String id){
        logger.info(">>>>>> apicontroller consulta funcionario por id");
        Optional<Funcionario> funcionario = funcionarioServico.consultarPorId(id);

        if(funcionario.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado com o ID: " + id);
        }
    }

    @CrossOrigin
    @PostMapping("/cadastro")
    public ResponseEntity <Object> cadastrarFuncionario(@RequestBody Funcionario f){
        logger.info(">>>>>> apicontroller cadastrar funcionario");
        Optional<Funcionario> funcionario = funcionarioServico.cadastrar(f);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario.get());
    }
    
    @CrossOrigin
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarPaciente(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado){
        logger.info(">>>>>>apicontroller atualizar funcionario iniciado");
        Optional<Funcionario> funcionario = funcionarioServico.atualizar(id, funcionarioAtualizado);
        
        if (funcionario.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionario não encontrado com o ID: " + id);
        }
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirFuncionario(@PathVariable String id) {
        Long idLong = Long.parseLong(id);
        logger.info(">>>>>>apicontroller excluir funcionario iniciado");

        try {
            funcionarioServico.excluir(idLong);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {//pesquisando para tratar esse essa expection
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionario não encontrado com o ID: " + id);
        }
    }
}