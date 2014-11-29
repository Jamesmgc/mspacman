package pacman.entries.ghosts;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Random;

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
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		String pathToStates = "C:/Users/james/Desktop/4thYear/AI/mspacman/stateMachine.txt";
		String[] arrayStates;
		
		myMoves.clear();
		
		try {
			StateReader sr = new StateReader(pathToStates);
			arrayStates = sr.openFile();
		} 
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		//Place your game logic here to play the game as the ghosts
		int targetNode=game.getPacmanCurrentNodeIndex();
		
		if(game.doesGhostRequireAction(GHOST.BLINKY))
			myMoves.put(GHOST.BLINKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
		
		if(game.doesGhostRequireAction(GHOST.INKY))
			myMoves.put(GHOST.INKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.INKY),targetNode,game.getGhostLastMoveMade(GHOST.INKY),DM.PATH));
		
		if(game.doesGhostRequireAction(GHOST.PINKY))
			myMoves.put(GHOST.PINKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.PATH));
		
		if(game.doesGhostRequireAction(GHOST.SUE))
			myMoves.put(GHOST.SUE,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.PATH));
		
		return myMoves;
	}
}