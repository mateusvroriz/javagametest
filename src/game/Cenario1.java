package game;

import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;
import jplay.URL;

public class Cenario1 {
	
	private Window janela;
	private Scene  cena;
	private Jogador jogador;
	private Keyboard teclado;
	private NPC npc;
	private gameObject parede;
	
	public Cenario1(Window window){
		
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario1.scn")); //carregando o arquivo com o mapa
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		Som.play("doido.mid");
		npc = new NPC(300,300);
		parede = new gameObject(600,300);
		run();
	}

	private void run(){
		while(true){
			jogador.mover(janela, teclado);
			jogador.caminho(cena, parede);
			
			npc.caminho(cena, parede);
			npc.perseguir(jogador.x, jogador.y);
			cena.moveScene(jogador); // centraliza o object passado como parametro
			
			npc.x += cena.getXOffset(); //incrementando a posição com o retorno pra melhorar a cmr
			npc.y += cena.getYOffset();
			
			parede.x += cena.getXOffset(); //incrementando a posição com o retorno pra melhorar a cmr
			parede.y += cena.getYOffset();
			jogador.x += cena.getXOffset(); //incrementando a posição com o retorno pra melhorar a cmr
			jogador.y += cena.getYOffset();
			npc.draw();
			jogador.draw();
			parede.draw();
			parede.update();
			janela.update();
			
		}
	}
}
