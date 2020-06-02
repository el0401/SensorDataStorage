package chess.gameMachine;

import chess.exceptions.MoveNotAllowedException;
import chess.exceptions.OutOfBoardException;
import chess.figures.Figure;
import chess.figures.FigureTypes;

import java.io.IOException;

public interface Board {

    void rollDice() throws IOException, StatusException;

    void move(int from, int to) throws MoveNotAllowedException;

    void move(int from, int to, FigureTypes pawnRuleType) throws MoveNotAllowedException;

    void rochade(int from) throws MoveNotAllowedException;

    Figure getFigure(int from) throws OutOfBoardException;

    BoardResults checkBoard();
}
