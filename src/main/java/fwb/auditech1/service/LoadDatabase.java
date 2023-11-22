
package fwb.auditech1.service;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import fwb.auditech1.model.Foto;
import fwb.auditech1.model.Funcionario;
import fwb.auditech1.model.IFotoRepository;
import fwb.auditech1.model.IFuncionarioRepository;
import fwb.auditech1.model.Paciente;
import fwb.auditech1.model.IPacienteRepository;
@Configuration
public class LoadDatabase {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	IFotoRepository fotoRepository;
	@Bean
	CommandLineRunner initDatabase(IPacienteRepository repository, IFuncionarioRepository repositoryF) {
		return args -> {
            Paciente paciente1 = new Paciente("Lince", "10291827682", "20/10/1992", "20202020", "Rua ficticia", "email@email.com", "202020", "Johnson", "00098273782");
            
            Paciente paciente2 = new Paciente("PAciente", "10222227682", "20/10/2000", "202111120", "Rua ficticia2", "email@email.com", "101010", "Baby", "87264527381");
        
            repository.saveAll(Arrays.asList(paciente1, paciente2));
            logger.info(">>>>> loaddatabase -> 2 pacientes cadastrados no db.");

            // Upload - obtém a foto do caminho, atribui ao objeto foto e salva no banco de dados do servidor

            Path path = Paths.get("src\\main\\resources\\produto1.jpg");
            InputStream file = Files.newInputStream(path);
            byte[] arquivo1 = file.readAllBytes();
            Foto foto = new Foto();
            foto.setId(1L); // associa o id do paciente ao id da foto
            foto.setNome("paciente1.jpg");
            foto.setCaminho("fotos/" + foto.getNome());
            foto.setArquivo(arquivo1);
            logger.info(">>>>> loaddatabase -> upload de arquivo foto realizado => " + arquivo1.length);
            fotoRepository.save(foto);

            path = Paths.get("src\\main\\resources\\produto2.jpg");
            file = Files.newInputStream(path);
            byte[] arquivo2 = file.readAllBytes();
            foto = new Foto();
            foto.setId(2L); // associa o id do paciente ao id da foto
            foto.setNome("paciente2.jpg");
            foto.setCaminho("fotos/" + foto.getNome());
            foto.setArquivo(arquivo2);
            logger.info(">>>>> loaddatabase -> upload de arquivo foto realizado => " + arquivo2.length);
            fotoRepository.save(foto);

            Funcionario funcionario1 = new Funcionario("Willian", "10291827222", "wllian@email.com",
            "Rua acai", "comsono");
            Funcionario funcionario2 = new Funcionario( "Funcione", "11222827682", "funcione@email.com",
            "Rua ladeira", "semsono");
            repositoryF.save(funcionario1);
            repositoryF.save(funcionario2);
            logger.info(">>>>> loaddatabase -> 2 funcionarios cadastrados no db.");
        };
    }
}
