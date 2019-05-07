package Escalonador.Unidade3;


import java.util.ArrayList;
import java.util.List;


public class EscalonadorPrioridade {
	private List<Escalonador> escalonadores = new ArrayList<Escalonador>();
	private int tick = 0;
	private String status;
	private int quantium;
	private int quantEscalonadores = 4;

	public EscalonadorPrioridade(int quantium) {
		this.quantium = quantium;
		for (int i = 0; i < this.quantEscalonadores ; i++) {
			this.escalonadores.add(new Escalonador (quantium));
		}
	}
	// Os gets que irão se juntar e tornar um retorno da lista
	public List<Escalonador> getEscalonadores() {
		return this.escalonadores;
	}
	public int getQuantium() {
		return this.quantium;
	}
	public String getStatus() {
		status = "Status: ";
		return status + this.geraStatus() + "    Tick: " + this.getTick() + "\n" + "    Quantium: "
				+ this.getQuantium();
	}
	// Gera status sera um for em vez de apenas ifs e else.
	public String geraStatus() {
		String statusComplemento = "";
		if (this.temProcesso() == false) {
			return "Nenhum processo\n";
		} else {
			for (int i = 0; i < this.escalonadores.size(); i++) {
				if (i > 0) {
					statusComplemento += "    ";
				}
				statusComplemento += i + 1 + " - ";
				if (this.escalonadores.get(i).temProcesso()) {
					statusComplemento += this.escalonadores.get(i).geraStatusComplemento();
				} else {
					statusComplemento += "\n";
				}
			}
		}
		return statusComplemento;
	}
	// Varrerar a lista de Escalonador para
	public boolean temProcesso() {
		for (Escalonador e : escalonadores) {
			if (e.temProcesso()) {
				return true;
			}
		}
		return false;
	}
	public int getTick() {
		return tick;
	}
	// Esse metodo intercala os processos e avança o tick
	public void avancarTick() {
		this.intercalaProcesso();
		this.tick++;
	}
	// Tornara um for para varrer os escalonadores e então usar o metodo intercalar
	// neles
	public void intercalaProcesso() {
		for (Escalonador e : this.escalonadores) {
			if (e.temProcessosExecutando()) {
				e.intercalaProcesso();
				return;
			}
		}
	}
	// Esse metodo ficaria bem menor com o escalonador como lista, e daria pra
	// fatorar essas linhas repetidas
	public void addProcesso(ProcessoPrioridade p) {
		this.getEscalonadores().get(p.getPrioridade()-1).addProcesso(p);
		for (int i=p.getPrioridade(); this.getEscalonadores().size()> i; i++) {
			if (this.getEscalonadores().get(i).temProcessosExecutando()) {
				this.getEscalonadores().get(i).executandoVaiParaEspera();
				break;
			}
		}
		if(this.getEscalonadores().get(p.getPrioridade()-1).temProcessosExecutando()){
			for (int i=p.getPrioridade()-2 ; 0 <= i; i--) {
				if(this.getEscalonadores().get(i).temProcessosExecutando()) {
					this.getEscalonadores().get(p.getPrioridade()-1).executandoVaiParaEspera();
					break;
				}
			}
		}
	}
	
	// Retorna -1 se não tiver o nome nos escalonadores, ou retorna o indice do escalonador que contem o processo
	public int processoInPrioridade(String nome) {
		for (int i = 0; this.getEscalonadores().size() > i; i++) {
			if(this.getEscalonadores().get(i).processoNoEscalonador(nome)) {
				return i;
			}
		}
		return -1;
	}

	// Lançar exceção de não encontrado.
	// Esse metodo poderia ser feito em um tamanho menor, usando o metodo de
	// processoInPrioridade(), visto que ele retornaria ja o escalonador na qual o
	// processo se encontra.
	public void finalizarProcesso(String nome) {
		int indice = this.processoInPrioridade(nome);
		if (indice >= 0) {
			this.getEscalonadores().get(indice).finalizarProcesso(nome);
			this.executarProximos(indice);
		}
	}

	// Lançar exceção de não encontrado.
	// Esse metodo poderia ser feito em um tamanho menor, usando o metodo de
	// processoInPrioridade(),
	// visto que ele retornaria ja o escalonador na qual o processo se encontra.
	public void bloquearProcesso(String nome) {
		int indice = this.processoInPrioridade(nome);
		if (indice >= 0) {
			this.getEscalonadores().get(indice).bloquearProcesso(nome);
			this.executarProximos(indice);
		}
	}
	
	public void executarProximos(int indice) {
		if (!this.getEscalonadores().get(indice).temProcessosExecutando()) {
			for (int i= indice; this.getEscalonadores().size() > i; i++) {
				if (this.getEscalonadores().get(i).haProcessoEsperando()) {
					this.getEscalonadores().get(i).esperandoVaiParaExecutar();
					return;
				}
			}
		}	
	}

	// Lançar exceção de não encontrado.
	// Esse metodo poderia ser feito em um tamanho menor, usando o metodo de
	// processoInPrioridade(),
	// visto que ele retornaria ja o escalonador na qual o processo se encontra.
	public void desbloquearProcesso(String nome) {
		int indice = this.processoInPrioridade(nome);
		if (indice >= 0) {
			this.getEscalonadores().get(indice).desbloquearProcesso(nome);
			for (int i= indice+1; this.getEscalonadores().size() > i; i++) {
				if(this.getEscalonadores().get(i).temProcessosExecutando()) {
					this.getEscalonadores().get(i).executandoVaiParaEspera();
					break;
				}
			}
			for (int i= indice -1; 0 <= i; i--) {
				if (this.getEscalonadores().get(i).temProcessosExecutando() && this.getEscalonadores().get(indice).temProcessosExecutando()) {
					this.getEscalonadores().get(indice).executandoVaiParaEspera();
				}
			}
		}
	}
	// Os metodos finalizar, bloquear e desbloquear eles precisam sempre
	// verificar se tem alguem com prioridade maior ou menor executando ou não para
	// que ele tome a posse do processador ou pare de executar
}