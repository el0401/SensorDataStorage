package chess.gameMachine;

import chess.figures.FigureTypes;

import java.io.IOException;

public interface Sender {

    void sendDie(int number) throws IOException;

    void chooseColor(boolean white) throws IOException;

    void move(int from, int to) throws IOException;

    void movePawnRule(int from, int to, FigureTypes figureType) throws IOException;

    void rochade(int form) throws IOException;

    void endGame(int reason) throws IOException;

    void proposeEnd(int reason) throws IOException;

    void proposeAnswer(boolean accept) throws IOException;
}
