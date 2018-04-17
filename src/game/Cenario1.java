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
	
	public Cenario1(Window window){
		
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario1.scn")); //carregando o arquivo com o mapa
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		
		run();
	}

	private void run(){
		while(true){
			jogador.mover(janela, teclado);
			jogador.caminho(cena);
			
			cena.moveScene(jogador); // centraliza o object passado como parametro
			jogador.x += cena.getXOffset(); //incrementando a posição com o retorno pra melhorar a cmr
			jogador.y += cena.getYOffset();
			jogador.draw();
			janela.update();
			
		}
	}
}
