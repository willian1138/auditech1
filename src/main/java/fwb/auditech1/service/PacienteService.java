package fwb.auditech1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import fwb.auditech1.model.Registro;
import fwb.auditech1.model.IPacienteRepository;
import fwb.auditech1.model.Paciente;

@Service
public class PacienteService implements IPacienteService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IPacienteRepository repositoryP;

    @Autowired
    FotoService fotoService;

    @Override
    public List<Registro> consultaRegistroPacientes() {
        Registro c = null;
        List<Registro> lista = new ArrayList<Registro>();
        List<Paciente> listaP = repositoryP.findAll();
        for (Paciente p : listaP) {
            c = new Registro(p.getId(), p.getNome(), p.getCpf());
            lista.add(c);
        }
        return lista;
    }

    @Override
    public List<Paciente> consultaPorNome() {
        throw new UnsupportedOperationException("Método 'consultaPorNome' não implementado");
    }

    @Override
    public Optional<Paciente> cadastrar(Paciente paciente) {
        logger.info("Serviço de cadastro de paciente iniciado");
        return Optional.ofNullable(repositoryP.save(paciente));
    }

    @Override
    public Optional<Paciente> consultarPorId(String id) throws ResourceNotFoundException {
        logger.info("Serviço de consulta de paciente por ID iniciado");
        Optional<Paciente> paciente = repositoryP.findById(Long.parseLong(id));
        if (paciente.isPresent()) {
            return paciente;
        } else {
            throw new ResourceNotFoundException("Paciente não encontrado para o ID '" + id + "'");
        }
    }

    @Override
    public Optional<Paciente> atualizar(Long id, Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteExistente = repositoryP.findById(id);

        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();

            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setCpf(pacienteAtualizado.getCpf());
            paciente.setDataDeNascimento(pacienteAtualizado.getDataDeNascimento());
            paciente.setNumeroDeTelefone(pacienteAtualizado.getNumeroDeTelefone());
            paciente.setEndereco(pacienteAtualizado.getEndereco());
            paciente.setEmail(pacienteAtualizado.getEmail());
            paciente.setSenha(pacienteAtualizado.getSenha());
            return Optional.ofNullable(repositoryP.save(paciente));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void excluir(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteExistente = repositoryP.findById(id);
        if (pacienteExistente.isPresent()) {
            repositoryP.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
