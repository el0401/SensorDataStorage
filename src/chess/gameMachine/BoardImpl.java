package chess.gameMachine;

import chess.exceptions.ErrorRecievedException;
import chess.exceptions.MoveNotAllowedException;
import chess.exceptions.OutOfBoardException;
import chess.exceptions.SameNumberException;
import chess.figures.Figure;
import chess.figures.FigureTypes;

import java.io.IOException;
import java.util.Random;

public class BoardImpl implements Board , Receiver {
    private GameStatus status;
    private final Sender sender;
    private int diceNr;

    public BoardImpl(Sender sender) {
        this.status = GameStatus.SPIELSTART;
        this.sender = sender;
    }

    @Override
    public void rollDice() throws StatusException, IOException {
        if (this.status != GameStatus.SPIELSTART) {
            throw new StatusException();
        }
        Random r = new Random();
        this.diceNr = r.nextInt();

        this.sender.sendDie(this.diceNr);

        this.status = GameStatus.WÜRFEL_GESENDET;

    }

    @Override
    public void move(int from, int to) throws MoveNotAllowedException {
        
    }

    @Override
    public void move(int from, int to, FigureTypes pawnRuleType) throws MoveNotAllowedException {

    }

    @Override
    public void rochade(int from) throws MoveNotAllowedException {

    }

    @Override
    public Figure getFigure(int from) throws OutOfBoardException {
        return null;
    }

    @Override
    public BoardResults checkBoard() {
        return null;
    }

    @Override
    public void waitForDice(int number) throws ErrorRecievedException, SameNumberException {

    }

    @Override
    public void waitForChoosenColor(boolean white) throws ErrorRecievedException {

    }

    @Override
    public void waitForMove(int from, int read) throws ErrorRecievedException {

    }

    @Override
    public void waitForProposeAnswer(boolean accept) throws ErrorRecievedException {

    }

    @Override
    public void waitForPawnMove(int from, int read, String figure) throws ErrorRecievedException {

    }

    @Override
    public void waitForRochade(int form) throws ErrorRecievedException {

    }

    @Override
    public void receiveEndGame(int reason) throws ErrorRecievedException {

    }
}
