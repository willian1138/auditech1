package fwb.auditech1.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IFotoRepository extends JpaRepository<Foto, Long> {
	Optional<Foto> findByNome(String nome);
}