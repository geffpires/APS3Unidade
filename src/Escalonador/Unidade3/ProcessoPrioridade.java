package Escalonador.Unidade3;

public class ProcessoPrioridade extends Processo{
	private int prioridade;
	
	//Não deixar ser criado processo sem prioridade, lançar exceção
	public ProcessoPrioridade(String nome, int prioridade) {
		super(nome);
		this.prioridade = prioridade;
		// TODO Auto-generated constructor stub
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	//adcionar 8 caracteres branco antes do status talvez seja na classe escalonadorPrioridade

}
