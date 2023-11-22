package fwb.auditech1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // manipula - lazy loaded properties
@Entity
@Data
public class Paciente {

 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
   	private String cpf; 
   	private String dataDeNascimento;
	private String numeroDeTelefone;
	private String endereco;
	private String email;
	private String senha;
    private String nomeDoResponsavel;
	private String cpfDoResponsavel;
    
	public Paciente(String nome, String cpf, String dataDeNascimento, String numeroDeTelefone, String endereco, String email,
	 String senha, String nomeDoResponsavel, String cpfDoResponsavel){
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.numeroDeTelefone = numeroDeTelefone;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.nomeDoResponsavel = nomeDoResponsavel;
		this.cpfDoResponsavel = cpfDoResponsavel;
		
	}
	public Paciente(){}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getNumeroDeTelefone() {
		return numeroDeTelefone;
	}
	public void setNumeroDeTelefone(String numeroDeTelefone) {
		this.numeroDeTelefone = numeroDeTelefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomeDoResponsavel() {
		return nomeDoResponsavel;
	}
	public void setNomeDoResponsavel(String nomeDoResponsavel) {
		this.nomeDoResponsavel = nomeDoResponsavel;
	}
	public String getCpfDoResponsavel() {
		return cpfDoResponsavel;
	}
	public void setCpfDoResponsavel(String cpfDoResponsavel) {
		this.cpfDoResponsavel = cpfDoResponsavel;
	}
		


	


}