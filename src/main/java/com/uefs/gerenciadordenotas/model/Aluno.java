package com.uefs.gerenciadordenotas.model;

public class Aluno {

	private int id;
	private String nome;
	private Double n1;
	private Double n2;
	private Double n3;
	private Aluno aluno;

	public Aluno(String nome, Double n1, Double n2, Double n3) {
		this.nome = nome;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		this.aluno = this;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Double getN1() {
		return n1;
	}

	public Double getN2() {
		return n2;
	}

	public Double getN3() {
		return n3;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setN1(Double n1) {
		this.n1 = n1;
	}

	public void setN2(Double n2) {
		this.n2 = n2;
	}

	public void setN3(Double n3) {
		this.n3 = n3;
	}

	public Double getMedia() {
		return (this.n1 + this.n2 + this.n3) / 3;
	}

	public String getSituacao() {
		if (this.getMedia() <= 7) {
			return "Reprovado";
		}
		return "Aprovado";
	}
	
	public Aluno getAluno() {
		return this.aluno;
	}

}
