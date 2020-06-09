package chess.gameMachine;

import chess.exceptions.ErrorRecievedException;
import chess.exceptions.SameNumberException;

public interface Receiver {

    void waitForDice(int number) throws ErrorRecievedException, SameNumberException;

    void waitForChoosenColor(boolean white) throws ErrorRecievedException;

    void waitForMove(int from, int read) throws ErrorRecievedException;

    void waitForProposeAnswer(boolean accept) throws ErrorRecievedException;

    void waitForPawnMove(int from, int read, String figure) throws ErrorRecievedException;

    void waitForRochade(int form) throws ErrorRecievedException;

    void receiveEndGame(int reason) throws ErrorRecievedException;
}
