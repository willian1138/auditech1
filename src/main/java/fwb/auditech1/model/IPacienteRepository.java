package fwb.auditech1.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository 
 
    public interface IPacienteRepository extends JpaRepository<Paciente, Long>{
        public List <Paciente> findAllByNomeIgnoreCaseContaining(String nome);


    }

