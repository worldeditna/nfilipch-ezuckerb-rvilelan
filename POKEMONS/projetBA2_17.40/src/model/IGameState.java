package model;

import java.util.List;

public interface IGameState 
{
    boolean                isFinished();
    boolean                hasWon();
    List<IPokemonState>    getFutureOpponents();
    IPokemon          	   getOpponent();
    IPokemon        	   getPlayer();
    List<IAction>          getPossibleMoves(int turns, int skill_used);
    IGameState             nextState(IAction action);
    int					   returnHP();
    int 				   returnMAXhp();
    int                    returnHPADV();
    int   				   returnMAXHPADV();
    void				   readTXT();
    String 				   contreatt();
    void				   filtreCheck();	
    void 					fightADV();
	boolean 				  superA();
    
}
