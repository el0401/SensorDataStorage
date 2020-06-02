package chess.gameMachine;

import chess.exceptions.ErrorRecievedException;
import chess.exceptions.SameNumberException;

public interface Reciever {

    void waitForDice() throws ErrorRecievedException, SameNumberException;

    void waitForChoosenColor() throws ErrorRecievedException;

    void waitForAction() throws ErrorRecievedException;

    void waitForProposeAnswer() throws ErrorRecievedException;
}
