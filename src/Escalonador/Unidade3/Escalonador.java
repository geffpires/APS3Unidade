package Escalonador.Unidade3;

import java.util.ArrayList;
import java.util.List;

public class Escalonador {
	
	private int quantium;
	private Processo executando;
	private List<Processo> processos = new ArrayList<Processo>();
	private List<Processo> bloqueados = new ArrayList<Processo>();
	private String status;
	private int tick = 0;
	

	
	public Escalonador(int quantium) {
		super();
		this.quantium = quantium;
	}
	public int getQuantium() {
		return quantium;
	}
	public void setQuantium(int quantium) {
		this.quantium = quantium;
	}
	public List<Processo> getProcessos() {
		return processos;
	}
	public String getStatus() {
		this.status = "Status: ";
		return this.status+this.geraStatusComplemento()+
				"    Tick: "+this.getTick()+"\n"+
				"    Quantium: "+this.getQuantium();
		
	}
	public String geraStatusComplemento() {
		String statusComplemento = "";
		if (this.processos.size() == 0 && this.executando == null && this.bloqueados.size() ==0) {
			return "Nenhum processo\n";
		}else if(this.executando != null) {
			statusComplemento += executando.getStatus()+"\n";
		}
		statusComplemento += this.geraStatus(this.processos)+this.geraStatus(this.bloqueados);
		return statusComplemento;
	}
	public String geraStatus(List<Processo> processos) {
		String statusDosProcessos = "";
		for (Processo p:processos) {
			statusDosProcessos+= p.getStatus()+"\n";
		};
		return statusDosProcessos;
		
	}
	public int getTick() {
		return tick;
	}
	public void avancarTick() {
		
		this.intercalaProcesso();
		this.tick++;
		
	}
	public void intercalaProcesso() {
		if(this.haProcessoEsperando()) {
			this.incrementaTempoNoEscalonador();
			if (this.estourouQuantium()) {
				this.executaProximoProcesso();
			}
		}
	}
	
	public boolean estourouQuantium() {
		if (this.executando.getQantTickNoEscalonador()>this.quantium) {
			return true;
		}
		return false;
	}
	public void executaProximoProcesso() {
		this.executandoVaiParaEspera();
		this.esperandoVaiParaExecutar();
	}
	public void executandoVaiParaEspera() {
		this.executando.setStatus("Esperando");
		this.executando.setQantTickNoEscalonador(0);
		this.processos.add(this.executando);
		this.executando = null;
		//trocar para os metodos gets e sets
	}
	public void esperandoVaiParaExecutar() {
		this.processos.get(0).setStatus("Executando");
		this.executando=this.processos.get(0);
		this.processos.remove(0);
		this.executando.setQantTickNoEscalonador(0);//garantindo que vai começar com 0 no tempo do escalonador
		//trocar para os metodos gets e set
	}
	public Processo getExecutando() {
		return executando;
	}
	public void setExecutando(Processo executando) {
		this.executando = executando;
	}
	public void addProcessoSemExecutar(Processo p) {
		this.processos.add(p);
	}
	public void addProcesso(Processo p) {
		if(this.processos.size() == 0 && this.executando == null) {
			p.setStatus("Executando");
			this.executando = p;
		}
		else {
			this.addProcessoSemExecutar(p);
		}
	}
	public List<Processo> getEsperasEBloqueados(){
		List<Processo> all = new ArrayList<Processo>();
			for (Processo p: this.getProcessos()) {
				all.add(p);
			}
			for (Processo p:this.getBloqueados()) {
				all.add(p);
			}
		return all;
	}
	public boolean processoNoEscalonador(String nome) {
		if (this.temProcessosExecutando()) {
			if(this.executando.getNome() == nome) {
				return true;
			}
		}
		for (Processo p: this.getEsperasEBloqueados()) {
			if (p.getNome()== nome) {
				return true;
			}
		}
		return false;
	}
	
	//Esse metodo retorna true se houver processos esperando
	public boolean haProcessoEsperando() {
		if(this.processos.size()>0) {
			return true;
		}
		return false;
	}
	
	//Esse metodo retorna true se houver processos bloqueados
	public boolean haProcessoBloqueado() {
		if(this.bloqueados.size() > 0) {
			return true;
		}
		return false;
	}
	
	//Esse metodo incrementa o tempo que o processo está no escalonador
	public void incrementaTempoNoEscalonador() {
		this.executando.incrementaTempoNoEscalonador();
	}

	public void finalizarProcesso(String nome) {
		if (this.temProcessosExecutando()) {
			if (executando.getNome() == nome) {
				this.executando = null;
				if (this.processos.size() > 0) {
					this.processos.get(0).setStatus("Executando");
					this.executando = this.processos.get(0);
					this.processos.remove(0);
				}
			}
		}
		for (Processo p : this.processos) {
			if (p.getNome() == nome) {
				this.processos.remove(p);
				break;
			}
		}
		for (Processo p : this.bloqueados) {
			if (p.getNome() == nome) {
				this.bloqueados.remove(p);
				break;
			}
		}
	}
	public void zeraQuantNoEscalonador() {
		this.executando.zerarTempoNoEscalonador();
	}
	public void bloquearProcesso(String nome) {
		// TODO Auto-generated method stub
		if (this.temProcessosExecutando()) {
			if (this.executando.getNome() == nome) {
				this.zeraQuantNoEscalonador();
				this.executando.setStatus("Bloqueado");
				this.bloqueados.add(executando);
				this.executando = null;
				if (this.haProcessoEsperando()) {
					this.executando = processos.get(0);
					this.executando.setStatus("Executando");
					this.zeraQuantNoEscalonador();
					this.processos.remove(0);
				}
			}
		}
		for (Processo p:processos) {
			if (p.getNome()==nome) {
				p.setStatus("Bloqueado");
				p.setQantTickNoEscalonador(0);
				this.bloqueados.add(p);
				this.processos.remove(p);
				break;
			}
		}
		
	}
	public void desbloquearProcesso(String nome) {
		// TODO Auto-generated method stub
		for(Processo p:this.bloqueados) {
			if(p.getNome()==nome) {
				p.setStatus("Esperando");
				this.addProcesso(p);
				this.bloqueados.remove(p);
				break;
			}
		}
	}
	
	//Este metodo retorna true se o escalonador tiver processos executando
	public boolean temProcessosExecutando() {
		if (this.executando != null) {
			return true;
		}
		return false;
	}
	
	//Este metodo retorna true se ouver algum tipo de processo dentro do escalonador
	public boolean temProcesso() {
		// TODO Auto-generated method stub
		if (this.temProcessosExecutando()) {
			return true;
		}else if(this.haProcessoEsperando()){
			return true;
		}else if(this.haProcessoBloqueado()) {
			return true;
		}
		return false;
	}
	public List<Processo> getBloqueados() {
		return this.bloqueados;
	}

}