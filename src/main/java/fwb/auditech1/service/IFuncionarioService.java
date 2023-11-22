package fwb.auditech1.service;

import fwb.auditech1.model.Funcionario;
import fwb.auditech1.model.Registro;

import java.util.List;
import java.util.Optional;

public interface IFuncionarioService {
    public List<Registro> consultaRegistroFuncionarios();
    public Optional<Funcionario> cadastrar(Funcionario funcionario);
    public Optional<Funcionario> consultarPorId(String id);
    public Optional<Funcionario> atualizar(Long id, Funcionario funcionario);
    public void excluir(Long id);   
}
