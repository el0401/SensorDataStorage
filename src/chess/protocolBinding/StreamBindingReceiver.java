package chess.protocolBinding;

import chess.exceptions.ErrorRecievedException;
import chess.exceptions.SameNumberException;
import chess.gameMachine.Receiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamBindingReceiver extends Thread {

    private final DataInputStream dis;
    private final Receiver receiver;

    public StreamBindingReceiver(InputStream is, Receiver receiver) {
        this.dis = new DataInputStream(is);
        this.receiver = receiver;
    }


    public void readDice() throws ErrorRecievedException, SameNumberException, IOException {
        int number = this.dis.readInt();

        this.receiver.waitForDice(number);
    }


    public void readChoosenColor() throws ErrorRecievedException, IOException {
        boolean white = this.dis.readBoolean();
        this.receiver.waitForChoosenColor(white);
    }


    public void readAction(int action) throws ErrorRecievedException, IOException {
        switch (action) {
            case StreamBinding.move:
                int from = this.dis.readInt();
                int read = this.dis.readInt();
                this.receiver.waitForMove(from,read);
                break;
            case StreamBinding.movePawn:
                from = this.dis.readInt();
                read = this.dis.readInt();
                String figure = this.dis.readUTF();
                this.receiver.waitForPawnMove(from,read,figure);
            case StreamBinding.rochade:
                int form = this.dis.readInt();
                this.receiver.waitForRochade(form);
            case StreamBinding.endgame:
                int reason = this.dis.readInt();
                this.receiver.receiveEndGame(reason);
        }
    }


    public void readProposeAnswer() throws ErrorRecievedException, IOException {
        boolean accept = this.dis.readBoolean();
        this.receiver.waitForProposeAnswer(accept);

    }

    public void run() {
        boolean again = true;
        while(again){
            try {
                int cmd = this.dis.readInt();

                switch (cmd) {
                    case StreamBinding.dice : this.readDice();break;
                    case StreamBinding.colour: this.readChoosenColor();break;
                    case StreamBinding.move: this.readAction(StreamBinding.move);
                    case StreamBinding.movePawn: this.readAction(StreamBinding.movePawn);
                    case StreamBinding.rochade: this.readAction(StreamBinding.rochade);
                    case StreamBinding.endgame: this.readAction(StreamBinding.endgame);
                    case StreamBinding.proposeAnswer: this.readProposeAnswer();
                    default: again = false; System.err.println("unknown command code: " + cmd);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SameNumberException e) {
                e.printStackTrace();
            } catch (ErrorRecievedException e) {
                e.printStackTrace();
            }
        }
    }
}
