package fwb.auditech1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import fwb.auditech1.model.Funcionario;
import fwb.auditech1.model.IFuncionarioRepository;
import fwb.auditech1.model.Paciente;
import fwb.auditech1.model.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class FuncionarioService implements IFuncionarioService {
    
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired 
    IFuncionarioRepository repositoryF;


    @Override
    public Optional<Funcionario> cadastrar(Funcionario funcionario) {
        logger.info(">>>servico cadastrar funcionario iniciado");
  
        return Optional.ofNullable(repositoryF.save(funcionario));
         
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public Optional<Funcionario> consultarPorId(String id) throws ResourceNotFoundException {
        logger.info(">>>servico consultar funcionario por id iniciado");
        Optional<Funcionario> funcionario = repositoryF.findById(Long.parseLong(id));
        if (funcionario.isPresent()) {
            return funcionario;
        } else {
            throw new ResourceNotFoundException("Funcionario não encontrado para o ID '" + id + "'");
        }
    }
    
    @Override
    public Optional<Funcionario> atualizar(Long id, Funcionario funcionarioAtualizado) {
        Optional<Funcionario> funcionarioExistente = repositoryF.findById(id);
        if(funcionarioExistente.isPresent()){
            Funcionario funcionario = funcionarioExistente.get();

            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setCpf(funcionarioAtualizado.getCpf());
            funcionario.setEmail(funcionarioAtualizado.getEmail());
            funcionario.setEndereco(funcionarioAtualizado.getEndereco());
            funcionario.setFuncao(funcionarioAtualizado.getFuncao());
            return Optional.ofNullable(repositoryF.save(funcionario));
        } else{
            return Optional.empty();
        }

    }
    @Override
    public void excluir(Long id) throws ResourceNotFoundException{
        logger.info(">>>servico excluir funcionario por id iniciado");
        Optional<Funcionario> funcionarioExistente = repositoryF.findById(id);
        if(funcionarioExistente.isPresent()){
            repositoryF.deleteById(id);
        
        } else {
            throw new ResourceNotFoundException();
        }
    }

	@Override
	public List<Registro> consultaRegistroFuncionarios() {
        Registro r = null;
        List<Registro> lista = new ArrayList <>();
        List<Funcionario> listaFuncionarios = repositoryF.findAll();
        for(Funcionario f : listaFuncionarios){
            r = new Registro();
            r.setId(f.getId());
            r.setNome(f.getNome());
            r.setFuncao(f.getFuncao());
            lista.add(r);
        }
        return lista;
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'consultaRegistroFuncionarios'");
	}
}
