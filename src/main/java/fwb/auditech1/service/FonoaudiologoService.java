package fwb.auditech1.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import fwb.auditech1.model.IFonoaudiologoRepository;
import fwb.auditech1.model.Fonoaudiologo;

import java.util.List;
import java.util.Optional;

@Service
public class FonoaudiologoService implements IFonoaudiologoService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IFonoaudiologoRepository repositoryF;

    public Optional<Fonoaudiologo> cadastrar(Fonoaudiologo fonoaudiologo) {
        logger.info("Serviço de cadastro de fonoaudiologo iniciado");
        return Optional.ofNullable(repositoryF.save(fonoaudiologo));
    }

    public Optional<Fonoaudiologo> consultarPorId(String id) {
        logger.info("Serviço de consulta de fonoaudiologo por ID iniciado");
        Optional<Fonoaudiologo> fonoaudiologo = repositoryF.findById(Long.parseLong(id));
        if (fonoaudiologo.isPresent()) {
            return fonoaudiologo;
        } else {
            throw new ResourceNotFoundException("Fonoaudiologo não encontrado para o ID '" + id + "'");
        }
    }

    public Optional<Fonoaudiologo> consultarPorCpf(String cpf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarPorId'");
    }
    

    public Optional<Fonoaudiologo> atualizar(Long id, Fonoaudiologo fonoaudiologo) {
        logger.info("Serviço de atualização de fonoaudiologo iniciado");
        Optional<Fonoaudiologo> fonoaudiologoAtual = repositoryF.findById(id);
        if (fonoaudiologoAtual.isPresent()) {
            fonoaudiologo.setId(id); // converter long para string
            return Optional.ofNullable(repositoryF.save(fonoaudiologo));
        } else {
            throw new ResourceNotFoundException("Fonoaudiologo não encontrado para o ID '" + id + "'");
        }
    }

    public void excluir(Long id) {
        logger.info("Serviço de exclusão de fonoaudiologo iniciado");
        Optional<Fonoaudiologo> fonoaudiologo = repositoryF.findById(id);
        if (fonoaudiologo.isPresent()) {
            repositoryF.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Fonoaudiologo não encontrado para o ID '" + id + "'");
        }
    }

    public void excluirPorCpf(String cpf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarPorId'");
    }
    

    public Optional<Fonoaudiologo> login(String cpf, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarPorId'");
    }  

}
