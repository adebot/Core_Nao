package noa.core.plan;

import java.util.ArrayList;

import nao.core.pathfinding.Map;
import nao.core.pathfinding.Node;

public class Plan {
	private ArrayList<ArrayList<Integer>> planMatrice;
	private int xNao;
	private int yNao;
	private int xDest;
	private int yDest;
	private Float echelle;
	private int directionNao;

	private ArrayList<Node> sortiesSecours;
	private Map<Node> myMap;


	public Plan(ArrayList<ArrayList<Integer>> plan, int xN, int yN, int xD, int yD, ArrayList<Node> sortieSecours, Map<Node> myMap, Float echelle2) {
		this.planMatrice = plan;
		this.xNao = xN;
		this.yNao = yN;
		this.xDest = xD;
		this.yDest = yD;
		this.echelle = echelle2;
		this.directionNao=1; 
		/*
		 * 0: vers le haut
		 * 1: vers la droite
		 * 2: vers le bas
		 * 3: vers la gauche
		 */
		this.sortiesSecours = sortieSecours;
		this.myMap = myMap;
	}

	public ArrayList<Node> getSortiesSecours() {
		return sortiesSecours;
	}

	public void setSortiesSecours(ArrayList<Node> sortiesSecours) {
		this.sortiesSecours = sortiesSecours;
	}

	public Map<Node> getMyMap() {
		return myMap;
	}

	public void setMyMap(Map<Node> myMap) {
		this.myMap = myMap;
	}

	public ArrayList<ArrayList<Integer>> getPlanMatrice() {
		return planMatrice;
	}

	public void setPlanMatrice(ArrayList<ArrayList<Integer>> planMatrice) {
		this.planMatrice = planMatrice;
	}

	public int getxNao() {
		return xNao;
	}

	public void setxNao(int xNao) {
		this.xNao = xNao;
	}

	public int getyNao() {
		return yNao;
	}

	public void setyNao(int yNao) {
		this.yNao = yNao;
	}

	public int getxDest() {
		return xDest;
	}

	public void setxDest(int xDest) {
		this.xDest = xDest;
	}

	public int getyDest() {
		return yDest;
	}

	public void setyDest(int yDest) {
		this.yDest = yDest;
	}

	public int getValueAt(int x, int y){
		return  planMatrice.get(y).get(x);
	}
	public void setValueAt(int x, int y, int value){
		planMatrice.get(y).set(x,value);
	}

	public int getDirectionNao() {
		return directionNao;
	}

	public void setDirectionNao(int directionNao) {
		this.directionNao = directionNao;
	}

	public Float getEchelle() {
		return echelle;
	}

	public void setEchelle(Float echelle) {
		this.echelle = echelle;
	}

}
