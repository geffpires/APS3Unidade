package Escalonador.Unidade3;

public class Processo {
	private String nome;
	private String status;
	private int qantTickNoEscalonador = 0;
	
	
	public int getQantTickNoEscalonador() {
		return qantTickNoEscalonador;
	}
	public void setQantTickNoEscalonador(int qantTickNoEscalonador) {
		this.qantTickNoEscalonador = qantTickNoEscalonador;
	}
	public Processo(String nome) {
		super();
		this.status = "Esperando";
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatus() {
		return this.nome+" ("+this.status+")";
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void incrementaTempoNoEscalonador() {
		this.qantTickNoEscalonador++;
	}
	public void zerarTempoNoEscalonador() {
		// TODO Auto-generated method stub
		this.qantTickNoEscalonador = 0;
		
	}

}
