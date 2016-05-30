/*    
    Copyright (C) 2012 http://software-talk.org/ (developer@software-talk.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nao.core.pathfinding;

/**
 * A simple Example implementation of a Node only overriding the sethCosts
 * method; uses manhatten method.
 */
public class Node extends AbstractNode {
	protected String name;

	public Node(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		// do other init stuff
	}
	public Node(int xPosition, int yPosition, String name) {
		super(xPosition, yPosition);
		this.name = name;
	}

	public void sethCosts(AbstractNode endNode) {
		this.sethCosts((absolute(this.getxPosition() - endNode.getxPosition())
				+ absolute(this.getyPosition() - endNode.getyPosition()))
				* BASICMOVEMENTCOST);
	}

	private int absolute(int a) {
		return a > 0 ? a : -a;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
