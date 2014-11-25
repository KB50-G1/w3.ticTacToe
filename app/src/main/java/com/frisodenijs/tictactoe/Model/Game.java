package com.frisodenijs.tictactoe.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Friso on 14/11/25.
 */
public class Game implements Serializable{

    private Board board;
    private ArrayList<Player> players;
    private int test;

    public Game(Player p1, Player p2) {
        this.board = new Board();
        this.players = new ArrayList<Player>();
        this.test = 0;
        players.add(p1);
        players.add(p2);
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getTest() {
        this.test = this.test + 2 ;
        return test;
    }
}
