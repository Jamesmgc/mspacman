package pacman.entries.ghosts;

import java.util.EnumMap;
import java.util.Random;

import pacman.controllers.Controller;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	MOVE[] moves=MOVE.values();
	Random rnd=new Random();
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		//Place your game logic here to play the game as the ghosts
		int targetNode=game.getPacmanCurrentNodeIndex();
		
		if(game.doesGhostRequireAction(GHOST.BLINKY))
			myMoves.put(GHOST.BLINKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
		
		if(game.doesGhostRequireAction(GHOST.INKY))
			myMoves.put(GHOST.INKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.INKY),targetNode,game.getGhostLastMoveMade(GHOST.INKY),DM.MANHATTAN));
		
		if(game.doesGhostRequireAction(GHOST.PINKY))
			myMoves.put(GHOST.PINKY,
					game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.EUCLID));
		
		if(game.doesGhostRequireAction(GHOST.SUE))
			myMoves.put(GHOST.SUE,moves[rnd.nextInt(moves.length)]);
		
		return myMoves;
	}
}