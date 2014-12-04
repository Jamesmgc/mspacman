package pacman.entries.ghosts;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Random;

import pacman.Executor;
import pacman.controllers.Controller;
import pacman.game.Constants;
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
	private Random rnd=new Random();
	String[][] states = Executor.arrayStates;
	private enum events {seepacman, isghostedible, atjunction};
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		//Place your game logic here to play the game as the ghosts
		int targetNode=game.getPacmanCurrentNodeIndex();
		
		//check for ghost events
		for(GHOST ghost : GHOST.values())	{
			switch (ghost){
				case BLINKY :
					if (game.isGhostEdible(GHOST.BLINKY)){
						Executor.blinkyEvent = "isghostedible";
					}
					else if (game.getDistance(game.getGhostCurrentNodeIndex(GHOST.BLINKY), targetNode, DM.PATH) <=1) {
						Executor.blinkyEvent = "nearPacman";
						System.out.println("close to pacman " + game.getDistance(game.getGhostCurrentNodeIndex(GHOST.BLINKY), targetNode, DM.PATH));
						//System.out.println("close to pacman ");
						
					}
					else if (game.wasPowerPillEaten()) {
						Executor.blinkyEvent = "eatenPill";
					}
					//else {
					//	Executor.blinkyEvent = "atjunction";
					//}
					//Once there's an event, go to the new state
					try {
						Executor.blinkyState = Executor.sr.getNextState(states, Executor.blinkyState, Executor.blinkyEvent);
					} 
					catch(IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				
				case INKY :
					if (game.isGhostEdible(GHOST.INKY)){
						Executor.inkyEvent = "isghostedible";
					}
					else if (game.getDistance(game.getGhostCurrentNodeIndex(GHOST.INKY), targetNode, DM.PATH) <= 10) {
						Executor.inkyEvent = "nearPacman";
					}
					//else {
					//	Executor.inkyEvent = "noUniqueEvent";	
					//}
					//Once there's an event, go to the new state
					try {
						Executor.inkyState = Executor.sr.getNextState(states, Executor.inkyState, Executor.inkyEvent);
					} 
					catch(IOException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case PINKY :
					if (game.isGhostEdible(GHOST.PINKY)){
						Executor.pinkyEvent = "isghostedible";
					}
					else if (game.getDistance(game.getGhostCurrentNodeIndex(GHOST.PINKY), targetNode, DM.PATH) <= 10) {
						Executor.pinkyEvent = "nearPacman";
					}
					//else {
					//	Executor.pinkyEvent = "noUniqueEvent";
					//}
					//Once there's an event, go to the new state
					try {
						Executor.pinkyState = Executor.sr.getNextState(states, Executor.pinkyState, Executor.pinkyEvent);
					} 
					catch(IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				
				case SUE :
					if (game.isGhostEdible(GHOST.SUE)){
						Executor.sueEvent = "isghostedible";
					}
					else if (game.getDistance(game.getGhostCurrentNodeIndex(GHOST.SUE), targetNode, DM.PATH) <= 10) {
						Executor.sueEvent = "nearPacman";
					}
					//else {
					//	Executor.sueEvent = "noUniqueEvent";
					//}
					//Once there's an event, go to the new state
					try {
						Executor.sueState = Executor.sr.getNextState(states, Executor.sueState, Executor.sueEvent);
					} 
					catch(IOException e) {
						System.out.println(e.getMessage());
					}
					break;
			}
		}
		
		
		//Decide a move for BLINKY based on its state
		if(game.doesGhostRequireAction(GHOST.BLINKY)){
			switch (Executor.blinkyState){
				case "roaming": 
					myMoves.put(GHOST.BLINKY,moves[rnd.nextInt(moves.length)]);
				break;
					
				case "aggressive": 
					myMoves.put(GHOST.BLINKY,game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),
									targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
				break;
					
				case "fleeing":
					myMoves.put(GHOST.BLINKY,game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),
							targetNode,game.getGhostLastMoveMade(GHOST.BLINKY),DM.PATH));
				break;
			}
		}
		
		//Decide a move for INKY based on its state
		if(game.doesGhostRequireAction(GHOST.INKY)){
			switch (Executor.inkyState){
				case "roaming": 
					myMoves.put(GHOST.INKY,moves[rnd.nextInt(moves.length)]);
				break;
				
				case "aggressive": 
					myMoves.put(GHOST.INKY,game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.INKY),
								targetNode,game.getGhostLastMoveMade(GHOST.INKY),DM.PATH));
				break;
					
				case "fleeing":
					myMoves.put(GHOST.INKY,game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(GHOST.INKY),
							targetNode,game.getGhostLastMoveMade(GHOST.INKY),DM.PATH));
				break;
			}
		}
		
		//Decide a move for PINKY based on its state
		if(game.doesGhostRequireAction(GHOST.PINKY)){
			switch (Executor.pinkyState){
				case "roaming": 
					myMoves.put(GHOST.PINKY,moves[rnd.nextInt(moves.length)]);
				break;
				
				case "aggressive": 
					myMoves.put(GHOST.PINKY,
						game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),
								targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.PATH));
				break;
					
				case "fleeing":
					myMoves.put(GHOST.PINKY,game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),
							targetNode,game.getGhostLastMoveMade(GHOST.PINKY),DM.PATH));
				break;
			}
		}
		
		//Decide a move for SUE based on its state
		if(game.doesGhostRequireAction(GHOST.SUE)){
			switch (Executor.sueState){
				case "roaming": 
					myMoves.put(GHOST.SUE,moves[rnd.nextInt(moves.length)]);
				break;
				
				case "aggressive": 
					myMoves.put(GHOST.SUE,game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.SUE),
								targetNode,game.getGhostLastMoveMade(GHOST.SUE),DM.PATH));
				break;
				
				case "fleeing":
					myMoves.put(GHOST.SUE,game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(GHOST.SUE),
							targetNode,game.getGhostLastMoveMade(GHOST.SUE),DM.PATH));
				break;
			}
		}
		
		return myMoves;
	}
}