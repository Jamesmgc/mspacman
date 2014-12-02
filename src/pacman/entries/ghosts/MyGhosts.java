package pacman.entries.ghosts;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Random;

import pacman.Executor;
import pacman.controllers.Controller;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import pacman.entries.ghosts.StateReader;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */

public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	MOVE[] moves=MOVE.values();
	/*String pathToStates = "C:/Users/James/Desktop/gitPacman/mspacman/stateMAchine.txt";
	String[][] arrayStates;*/
	//String currState;
	//String mState = Executor.currState;
	
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		//System.out.println("current ghost state: " + mState);
		myMoves.clear();
		
		/*try {
			StateReader sr = new StateReader(pathToStates);
			arrayStates = sr.openFile();
			currState = arrayStates[0][0];
			//System.out.println("array value: " + arrayStates[6][0]);
			System.out.println("current state: " + currState);
		} 
		catch(IOException e) {
			System.out.println(e.getMessage());
		}*/
		
		//Place your game logic here to play the game as the ghosts
		int targetNode=game.getPacmanCurrentNodeIndex();
		
		//loop through all ghosts
		//String str = game.getGhostCurrentState(GHOST.BLINKY);
		//System.out.println(str);
		String str = game.getGhostCurrentState(GHOST.BLINKY);
		if(str == "roaming"){
			switch (game.getGhostCurrentState(GHOST.BLINKY)){
				case  "roaming": 
					myMoves.put(GHOST.BLINKY,
							game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),
									targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
					break;
				case  "agressive": 
					myMoves.put(GHOST.BLINKY,
							game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),
									targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
					break;
			}
		}
		//Executor.arrayStates;
		/*if(game.doesGhostRequireAction(GHOST.BLINKY)){
			if (game.getGhostCurrentState(GHOST.BLINKY) == "roaming") {
			myMoves.put(GHOST.BLINKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
			}
		}*/
		if(game.doesGhostRequireAction(GHOST.INKY)){
			if (game.getGhostCurrentState(GHOST.INKY) == "roaming") {
			myMoves.put(GHOST.INKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.INKY),targetNode,game.getGhostLastMoveMade(GHOST.INKY),DM.PATH));
		}}
		if(game.doesGhostRequireAction(GHOST.PINKY)){
			if (game.getGhostCurrentState(GHOST.PINKY) == "roaming") {
			myMoves.put(GHOST.PINKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.PATH));
		}}
		if(game.doesGhostRequireAction(GHOST.SUE)){
			if (game.getGhostCurrentState(GHOST.SUE) == "roaming") {
			myMoves.put(GHOST.SUE,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.PATH));
		}}
		
		return myMoves;
	}
}