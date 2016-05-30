package nao.core.lanceurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

import noa.core.plan.Plan;
import nao.core.pathfinding.ExampleFactory;
import nao.core.pathfinding.Map;
import nao.core.pathfinding.Node;
import nao.core.thread.Intervention;

public class LanceurMatrice {

	public static void main(String[] args) {
		String filePath = "plan1.txt";

		Plan monPlan = createPlan(filePath);

        String robotUrl = "tcp://192.168.1.101:9559";
        Application application = new Application(args, robotUrl);
        application.start();
        //ALTextToSpeech tts = new ALTextToSpeech(application.session());
        //tts.say("je marche");
        
		Intervention interThread = new Intervention();
		interThread.setMonPlan(monPlan);
		interThread.setApplication(application);
		interThread.start();
		interThread.setRun(true);
		
		Scanner sc = new Scanner(System.in);
		String saisi="";
		while (saisi ==""){
			saisi =sc.nextLine();
		}
		sc.close();
		interThread.setExit(true);
		application.stop();
	}

	private static void rechercheSortieNao(Plan monPlan) {
		Map<Node> myMap = monPlan.getMyMap();
		ArrayList<Node> listeSorties = monPlan.getSortiesSecours();
		String nomSortie="";
		int nombredeplacement =-1;
		for(int i=0;i<listeSorties.size();i++){
			List<Node> path = myMap.findPath(monPlan.getxNao(), monPlan.getyNao(), listeSorties.get(i).getxPosition(), listeSorties.get(i).getyPosition());
			if (nombredeplacement != -1 && path.size()<nombredeplacement){
				nombredeplacement = path.size();
				nomSortie = listeSorties.get(i).getName();
			} else if(nombredeplacement == -1){
				nombredeplacement = path.size();
				nomSortie = listeSorties.get(i).getName();
			}
		}
		System.out.println(nombredeplacement);
		System.out.println(nomSortie);

	}

	private static void aetoile(Plan monPlan) {
		Map<Node> myMap = monPlan.getMyMap();

		List<Node> path = myMap.findPath(monPlan.getxNao(), monPlan.getyNao(), monPlan.getxDest(), monPlan.getyDest());
		for (int i = 0; i < path.size(); i++) {
			System.out.print("(" + path.get(i).getxPosition() + ", " + path.get(i).getyPosition() + ") -> ");
		}
	}

	private static Plan createPlan(String filePath) {
		ArrayList<ArrayList<Integer>> plan = new ArrayList<>();
		int xN=-1,yN=-1,xD=-1,yD=-1;
		int xSize=0,ySize=0;
		Float echelle=0f;
		String line;
		ArrayList<Node> sortieSecours = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(filePath));
			int y=0,x=0;
			if (sc.hasNext()){
				line = sc.next();
				xSize = Integer.valueOf(line.split("x")[0]);
				ySize= Integer.valueOf(line.split("x")[1]);
				echelle = Float.valueOf(line.split("x")[2]);
			}
			Map<Node> myMap = new Map<Node>(xSize, ySize, new ExampleFactory());
			while (sc.hasNext()){
				plan.add(new ArrayList<>());
				line = sc.next();
				String[] cells = line.split(";");
				x=0;
				for (String cellule:cells){
					plan.get(plan.size()-1).add(Integer.valueOf(cellule.substring(0,1)));
					if (Integer.valueOf(cellule.substring(0,1))==1){
						myMap.setWalkable(x, y, false);
					} else if (Integer.valueOf(cellule.substring(0,1))==2){
						xN = x;
						yN = y;
					} else if (Integer.valueOf(cellule.substring(0,1))==3){
						yD = y;
						xD = x;
					} else if (Integer.valueOf(cellule.substring(0,1))==4){
						sortieSecours.add(new Node(x, y,cellule.substring(1)));
					}
					x++;
				}
				y++;
			}
			sc.close();
			return new Plan(plan,xN,yN,xD,yD,sortieSecours, myMap, echelle);
		} catch (FileNotFoundException e) {
			System.err.println("Could not open file");
			e.printStackTrace();
			return null;
		}
	}
}
