package Escalonador.Unidade3;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testeOrganizado {

	//Testes organizados :)
	@Test//T1
	public void escalonadorVazio() {
		Escalonador e = new Escalonador(3);
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T2
	public void tickIncrementou_() {
		Escalonador e = new Escalonador(3);
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//T3
	public void addProcessoTick0() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T4
	public void finalizarProcesso() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		e.finalizarProcesso("P1");
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//T5
	public void estourarTickTrocarProcesso() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P1 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
	}
	@Test//T6
	public void add3Processos() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T7
	public void addProcessoTick0ETick3() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(), "Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
		assertEquals(e.getExecutando().getQantTickNoEscalonador(),1);
	}
	@Test//T8
	public void finalizarProcessoExecutandoProximoProcessoEntra() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
		e.finalizarProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//T9
	public void finalizaProcessoEmEspera() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
		e.finalizarProcesso("P2");
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
	}
	@Test//10
	public void estourarQuantDiferente() {
		Escalonador e = new Escalonador(2);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 2");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P1 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 2");
	}
	@Test//T11
	public void doisProcessosIntervaloNoMeio() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
		+ "    Tick: 2\n"
		+ "    Quantium: 3");
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//T12
	public void processoP1CombloqueioQuantiumEstouraEntreOsOutros() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.bloquearProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P3 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3");
	}
	@Test//13
	public void retornarProcessoBloqueado() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.bloquearProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.avancarTick();
		e.desbloquearProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//T14
	public void todosProcessosBloqueadosERetornamEmOutraOrdem() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		e.avancarTick();
		e.bloquearProcesso("P1");
		e.bloquearProcesso("P2");
		e.bloquearProcesso("P3");
		e.avancarTick();
		e.desbloquearProcesso("P2");
		e.desbloquearProcesso("P1");
		e.desbloquearProcesso("P3");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P1 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//T16
	public void addProcessoTick0Prioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T17
	public void finalizarProcessoNaPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T17
	public void finalizarProcessoNaPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T17
	public void finalizarProcessoNaPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T17
	public void finalizarProcessoNaPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	
	@Test//18
	public void testEscalaNoMesmoNivel1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//18
	public void testEscalaNoMesmoNivel2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//18
	public void testEscalaNoMesmoNivel3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//18
	public void testEscalaNoMesmoNivel4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	//
	@Test//19
	public void testEscalaNoMesmoNivel1_3Processo() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P3",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//19
	public void testEscalaNoMesmoNivel2_3Processo() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		ep.addProcesso(new ProcessoPrioridade("P3",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P2 (Executando)\nP3 (Esperando)\nP1 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//19
	public void testEscalaNoMesmoNivel3_3Processo() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		ep.addProcesso(new ProcessoPrioridade("P3",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P2 (Executando)\nP3 (Esperando)\nP1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//19
	public void testEscalaNoMesmoNivel4_3Processo() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		ep.addProcesso(new ProcessoPrioridade("P3",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\nP3 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P2 (Executando)\nP3 (Esperando)\nP1 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//20
	public void comcorrenciaSoComMaisDe1Prcesso() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
		assertEquals(ep.getEscalonadores().get(0).getExecutando().getQantTickNoEscalonador(),1); //O getQantTickNoEscalonador diz a quantos tick o processo está com concorrencia
	}
	@Test//T21
	public void finalizarProcessoExecutandoNaPrioridade1ComConcorrencia() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//22
	public void finalizaProcessoEsperandoEExecutandoNaoPerdeCPU() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//24
	public void add2ProcessosEmPrioridadeDiferenteComIntervaloNoMeio() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P2 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P2 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//25
	public void add3ProcessoP1BloqueiaIntercalaOsOutros() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P3",1));
		ep.bloquearProcesso("P1");
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P3 (Executando)\nP2 (Esperando)\nP1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//26
	public void add3ProcessoP1DesbloqueiaIntercala() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P3",1));
		ep.bloquearProcesso("P1");
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP3 (Esperando)\nP1 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P3 (Executando)\nP1 (Esperando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//27
	public void bloquear3ProcessoRetornarOrdemDiferente() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P3",1));
		ep.bloquearProcesso("P1");
		ep.bloquearProcesso("P2");
		ep.bloquearProcesso("P3");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Bloqueado)\nP2 (Bloqueado)\nP3 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.desbloquearProcesso("P3");
		ep.desbloquearProcesso("P1");
		ep.desbloquearProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P3 (Executando)\nP1 (Esperando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//28
	public void continuaExecutandoPrioridadeMaior() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - P2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 6\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - P2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 10\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		ep.avancarTick();
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - P2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 13\n"
				+ "    Quantium: 3"));
		//Test 29
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Bloqueado)\n"
				+ "    2 - P2 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 13\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Bloqueado)\n"
				+ "    2 - P2 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 15\n"
				+ "    Quantium: 3"));
		// Test 30
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - P2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 15\n"
				+ "    Quantium: 3"));
	}
	@Test//Hiper Teste Merda Grandão Final
	public void testeVilarFinal() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//1
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//2
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//3
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.bloquearProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//4
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//5
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P3",4));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//6
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 6\n"
				+ "    Quantium: 3"));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Executando)\n"
				+ "    Tick: 6\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//7
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Executando)\n"
				+ "    Tick: 7\n"
				+ "    Quantium: 3"));
		ep.desbloquearProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 7\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//8
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 8\n"
				+ "    Quantium: 3"));
		//Teste rodando até aqui, ok
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 8\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P4",1));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 8\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//9
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 9\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//10
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 10\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P5",2));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 10\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P6",2));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 10\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//11
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 11\n"
				+ "    Quantium: 3"));
		assertEquals(ep.getEscalonadores().get(0).getExecutando().getQantTickNoEscalonador(),3);
		ep.avancarTick();//12
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\nP2 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 12\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//13
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\nP2 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 13\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//14
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\nP2 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 14\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//15
		assertEquals(ep.getEscalonadores().get(0).getExecutando().getQantTickNoEscalonador(),3);
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\nP2 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 15\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//16
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 16\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//17
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 17\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//18
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 18\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//19
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 19\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//20
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\nP2 (Esperando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 20\n"
				+ "    Quantium: 3"));
		ep.bloquearProcesso("P4");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 20\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//21
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 21\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//22
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 22\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//23
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 23\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//24
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 24\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//25
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 25\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//26
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 26\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//27
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 27\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//28
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 28\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//29
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 29\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//30
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP4 (Bloqueado)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 30\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P4");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 30\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//31
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 31\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//32
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - P5 (Esperando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 32\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Executando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 32\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//33
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Executando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 33\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//34
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Executando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 34\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//35
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Executando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 35\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//36 --
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 36\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//37
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 37\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//38
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 38\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//39
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 39\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//40--
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Executando)\nP6 (Esperando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 40\n"
				+ "    Quantium: 3"));
		ep.bloquearProcesso("P5");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 40\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//41
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 41\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//42
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 42\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//43
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 43\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//44
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 44\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//45
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 45\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//46
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P6 (Executando)\nP5 (Bloqueado)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 46\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P6");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Bloqueado)\n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 46\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//47*/
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P5 (Bloqueado)\n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 47\n"
				+ "    Quantium: 3"));
		
	}
	
	//Fim dos testes organizados (:

}