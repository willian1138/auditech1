package fwb.auditech1.model;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IFonoaudiologoRepository extends JpaRepository<Fonoaudiologo, Long> {
    public Fonoaudiologo findByCpf(String cpf);
    public Fonoaudiologo deleteByCpf(String cpf);
}
