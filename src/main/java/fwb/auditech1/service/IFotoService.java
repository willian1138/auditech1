package fwb.auditech1.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import fwb.auditech1.model.Foto;


public interface IFotoService {
	public Optional<Foto> salvar(long id, MultipartFile arquivo) throws IOException;
    public List < Foto > getAll();
    public byte[] getFoto(String nomeArquivo);
    public byte[] getFotoById(Long id);
}