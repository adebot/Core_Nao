package nao.core.thread;

import java.util.List;

import com.aldebaran.qi.Application;

import nao.core.deplacements.Avancer;
import nao.core.deplacements.TournerDroite;
import nao.core.deplacements.TournerGauche;
import nao.core.pathfinding.Node;
import noa.core.plan.Plan;

public class Intervention extends Thread{

	private Plan monPlan;
	private boolean run = false;
	private boolean exit = false;
	private Application application;
	
	@Override
	public void run() {
		List<Node> path = null;
		Avancer avancer = new Avancer(application, monPlan.getEchelle());
		TournerDroite td = new TournerDroite(application);
		TournerGauche tg = new TournerGauche(application);
		
		while (!exit){
			if (!exit && run){
				if (path == null) {
					path = monPlan.getMyMap().findPath(monPlan.getxNao(), monPlan.getyNao(), monPlan.getxDest(), monPlan.getyDest());
				} else {
					if (monPlan.getxNao() == monPlan.getxDest() && monPlan.getyDest()==monPlan.getyNao()){
						System.out.println("Destination atteinte. Coupure de la séquence de déplacement. En attente d'ordre...");
						run = false;
					} else {
						//récupération de la case suivante et de la position actuelle ainsi que de la direction
						int xCaseSuivante = path.get(0).getxPosition();
						int yCaseSuivante = path.get(0).getyPosition();

						if (monPlan.getxNao() < xCaseSuivante){
							switch (monPlan.getDirectionNao()) {
							case 0: //regarde au "nord"
								System.out.println("tourne a droite");
								td.run();
								monPlan.setDirectionNao(1);
								break;
							case 1: //regarde au "Est"
								break;
							case 2: //regarde au "Sud"
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(1);
								break;
							case 3: //regarde au "Ouest"
								System.out.println("tourne a gauche");
								tg.run();
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(1);
								break;
							}
						} else if (monPlan.getxNao() > xCaseSuivante){
							switch (monPlan.getDirectionNao()) {
							case 0: //regarde au "nord"
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(1);
								break;
							case 1: //regarde au "Est"
								System.out.println("tourne a gauche");
								tg.run();
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(3);
								break;
							case 2: //regarde au "Sud"
								System.out.println("tourne a droite");
								td.run();
								monPlan.setDirectionNao(3);
								break;
							case 3: //regarde au "Ouest"
								break;
							}
						} else if (monPlan.getyNao() < yCaseSuivante){
							switch (monPlan.getDirectionNao()) {
							case 0: //regarde au "nord"
								System.out.println("tourne a gauche");
								tg.run();
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(2);
								break;
							case 1: //regarde au "Est"
								System.out.println("tourne a droite");
								td.run();
								monPlan.setDirectionNao(2);
								break;
							case 2: //regarde au "Sud"
								break;
							case 3: //regarde au "Ouest"
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(2);
								break;
							}
						} else if (monPlan.getyNao() > yCaseSuivante) {
							switch (monPlan.getDirectionNao()) {
							case 0: //regarde au "nord"
								break;
							case 1: //regarde au "Est"
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(0);
								break;
							case 2: //regarde au "Sud"
								System.out.println("tourne a gauche");
								tg.run();
								System.out.println("tourne a gauche");
								tg.run();
								monPlan.setDirectionNao(0);
								break;
							case 3: //regarde au "Ouest"
								System.out.println("tourne a droite");
								td.run();
								monPlan.setDirectionNao(0);
								break;
							}
						}

						System.out.println("Avance");
						avancer.run();
						// Affectation des nouvelles coordonnées de Nao
						monPlan.setxNao(xCaseSuivante);
						monPlan.setyNao(yCaseSuivante);
						path.remove(0);
					}
				}
			}
		}
		System.out.println("Thread terminé");
	}

	public Plan getMonPlan() {
		return monPlan;
	}

	public void setMonPlan(Plan monPlan) {
		this.monPlan = monPlan;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
