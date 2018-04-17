package game;

import java.awt.Point;
import java.util.Vector;

import jplay.GameObject;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

public class Jogador extends Sprite{
	protected int direcao = 3;
	
	
	private double velocidade = 1;
	private boolean movendo = false;
	
	
	
	public Jogador(int x, int y) { // parameters that set the player initial location
		super(("src//recursos//sprite//jogador2.png"), 20); // img and frame number
		this.x = x; // setting the x and y to the passed cordinates
		this.y = y;
		this.setTotalDuration(2000); //millisecconds
		
		
	}
	
	public void mover(Window janela, Keyboard teclado){ //public because it's being acessed in the scenario
		
		
		if(teclado.keyDown(Keyboard.LEFT_KEY)){
			if(this.x >0){
				this.x -= velocidade; //evitando o jogador sair da tela
			}
			if(direcao !=1){
				setSequence(4,8); // definindo sprites esquerda
				direcao =1 ;
			} movendo = true;
		}else if(teclado.keyDown(Keyboard.RIGHT_KEY)){
			if(this.x < janela.getWidth() - 60 ){ // 60 pixeis do jogador
				this.x += velocidade; //evitando o jogador sair da tela
			}
			if(direcao !=2){
				setSequence(8,12); // definindo sprites direita
				direcao =2 ;
			} movendo = true;
		}else if(teclado.keyDown(Keyboard.UP_KEY)){
			
			if(this.y >0){
				this.y -= velocidade; //evitando o jogador sair da tela
			}
			if(direcao !=4){
				setSequence(12,16); // definindo sprites subindo
				direcao = 4 ;
			} movendo = true;
		}else if(teclado.keyDown(Keyboard.DOWN_KEY)){
			if(this.y < janela.getHeight() - 60){
				this.y += velocidade; //evitando o jogador sair da tela
			}
			if(direcao !=5){
				setSequence(0,4); // definindo sprites descendo
				direcao =5 ;
			} movendo = true;
			
		}
		if(movendo == true){
			update(); //updating the sprites
			movendo = false;
		}
	}
	
	Controle controle  = new Controle();
	/**
	 * Colisões com cenario tiles
	 * @param cena
	 */
	public void caminho(Scene cena){
		Point min = new Point((int)this.x,(int)this.y); 
		Point max = new Point((int)this.x + this.width,(int)this.y + this.height); 
		
		Vector<?>tiles = cena.getTilesFromPosition(min,max); //retornar as imagens que estiverem no msm lugar do personagem
		
		for(int i =0; i < tiles.size(); i++){
			TileInfo tile = (TileInfo) tiles.elementAt(i);
		
			if(controle.colisao(this, tile) == true){
				if(colisaoVertical(this, tile )){
					if(tile.y + tile.height -2 < this.y ){ // 2 margem de segurança 
						this.y = tile.y + tile.height; //reposicionando o personagem fora do tile
					}
					else if(tile.y > this.y+ this.height -2){ // 2 margem de segurança 
							this.y = tile.y - this.height;
						}
					}
					if(colisaoHorizontal(this, tile) == true){
						if(tile.x > this.x + this.width -2 ){
							this.x = tile.x - this.width;
						}else{
							this.x = tile.x + tile.width;
						}
				
				}
			}
		}
	}
	private boolean colisaoVertical(GameObject obj, GameObject obj2){
		if(obj2.x + obj2.width <= obj.x){ //verificando se o ponto x é maior ou igual ao ponto x do personagme
			return false;		
		}
		
		if(obj.x + obj.width <= obj2.x){
			return false;
		}
		return true; // se retornar isso tem colisao
	}
	
	private boolean colisaoHorizontal(GameObject obj, GameObject obj2){
		if(obj2.y + obj2.height <= obj.y){
			return false;
		}
		
		if(obj.y +obj.height <= obj2.y){
			return false;
		}
		return true;
	}

}
