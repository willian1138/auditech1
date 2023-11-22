package fwb.auditech1.service;

import java.util.Optional;

import fwb.auditech1.model.Fonoaudiologo;

public interface IFonoaudiologoService {
    public Optional<Fonoaudiologo> cadastrar(Fonoaudiologo fonoaudiologo);
    public Optional<Fonoaudiologo> consultarPorId(String id);
    public Optional<Fonoaudiologo> consultarPorCpf(String cpf);
    public Optional<Fonoaudiologo> atualizar(Long id, Fonoaudiologo fonoaudiologo);
    public void excluir(Long id);
    public Optional<Fonoaudiologo> login(String cpf, String senha);
    public void excluirPorCpf(String cpf);
}
