package fwb.auditech1.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import fwb.auditech1.model.Foto;
import fwb.auditech1.model.IFotoRepository;
import fwb.auditech1.model.Paciente;
import fwb.auditech1.model.IPacienteRepository;

@Service
public class FotoService implements IFotoService {

    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private IFotoRepository fotoRepository;

    	@Autowired
	private IPacienteRepository produtoRepository;
	public Optional<Foto> salvar(long id, MultipartFile arquivo) throws IOException {
	// ********************************************************** 
	// Obter informações sobre o arquivo - espera um tipo formdata chave/file 
	// ********************************************************** 
	logger.info(">>>>>> servico salvar imagem - iniciado...");
	Optional < Paciente > p = produtoRepository.findById(id);
	if (p.isPresent()) {
		logger.info(">>>>>> servico salvar imagem - produto encontrado");
		String nome = arquivo.getOriginalFilename();
		Path caminhoArquivo = Paths.get("imagens/" + nome);
		logger.info(">>>>>> servico salvar imagem - caminho arquivo => " + caminhoArquivo);
		Foto foto = new Foto();
		foto.setId(id); // associa o id do produto ao id da imagem
		foto.setNome(arquivo.getOriginalFilename());
		foto.setCaminho(caminhoArquivo.toString());
		foto.setArquivo(arquivo.getBytes());
		logger.info(">>>>>> servico salvar imagem - arquivo getSize => " + arquivo.getSize());
		// ********************************************************** 
		// salva no disco e no db 
		// *********************************************************** 
		//Files.write(caminhoArquivo, arquivo.getBytes());
		return Optional.of(fotoRepository.save(foto));
	} else {
		logger.info(">>>>>> servico salvar imagem - id nao encontrado");
		return Optional.empty();
	}
} 
 public List < Foto > getAll() {
	return fotoRepository.findAll();
}
 /** 
 * Download de imagens - se o nome do arquivo de imagem existir no db retorna 
 * imagem senao retorna vazio parametro - nome do arquivo de imagem a ser 
 * baixado. 
 */ 
@Override
 public byte[] getFoto(String nomeArquivo) {
	Optional < Foto > dbImagem = fotoRepository.findByNome(nomeArquivo);
	if (dbImagem.isPresent())
		return dbImagem.get().getArquivo();
	else
		return new byte[0];
}
@Override 
 public byte[] getFotoById(Long id) {
	Optional < Foto > dbImagem = fotoRepository.findById(id);
	if (dbImagem.isPresent())
		return dbImagem.get().getArquivo();
	else
		return new byte[0];
}}