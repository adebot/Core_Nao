/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nao.core.deplacements;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;

/**
 *
 * @author Belkebir
 */
public class Avancer {


	private static Application application;
	private float echelle;

	public Avancer(Application app, Float float1){
		application = app;
		echelle = float1;
	}

	public void run(){
		float x = 1f*echelle;    
		float y = 0f*echelle;
		float angle = 0f;

		ALMotion motion;
		try {
			motion = new ALMotion(application.session());
			motion.moveInit();
			motion.moveTo(x,y,angle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fin d'instruction");
	}

}