package game;

import jplay.GameObject;
import jplay.TileInfo;

public class Controle {
	public boolean colisao(GameObject obj, GameObject obj2){
		if(obj.collided(obj2)){
			return true;
			
		}
		return false;
		
	}
	
}
