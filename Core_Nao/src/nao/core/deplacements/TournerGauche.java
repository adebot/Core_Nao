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
public class TournerGauche {
	
	private static Application application;

	public TournerGauche(Application app) {
		application = app;
	}


	public void run()  {
		float x = 0f;    
		float y = 0f;
		float angle = 1.48f;

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