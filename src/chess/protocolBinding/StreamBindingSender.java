package chess.protocolBinding;

import chess.figures.FigureTypes;
import chess.gameMachine.Sender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamBindingSender implements Sender {

    private final DataOutputStream dos;

    public StreamBindingSender(OutputStream os) {
        this.dos = new DataOutputStream((os));
    }

    @Override
    public void sendDie(int number) throws IOException {
        this.dos.writeInt(StreamBinding.dice);
        this.dos.writeInt(number);
    }

    @Override
    public void chooseColor(boolean white) throws IOException {
        this.dos.writeInt(StreamBinding.colour);
        this.dos.writeBoolean(white);
    }

    @Override
    public void move(int from, int to) throws IOException {
        this.dos.writeInt(StreamBinding.move);
        this.dos.writeInt(from);
        this.dos.writeInt(to);
    }

    @Override
    public void movePawnRule(int from, int to, FigureTypes figureType) throws IOException {
        this.dos.writeInt(StreamBinding.movePawn);
        this.dos.writeInt(from);
        this.dos.writeInt(to);
        this.dos.writeUTF(String.valueOf(figureType));
    }

    @Override
    public void rochade(int form) throws IOException {
        this.dos.writeInt(StreamBinding.rochade);
        this.dos.writeInt(form);
    }

    @Override
    public void endGame(int reason) throws IOException {
        this.dos.writeInt(StreamBinding.endgame);
        this.dos.writeInt(reason);
    }

    @Override
    public void proposeEnd(int reason) throws IOException {
        this.dos.writeInt(StreamBinding.proposeEnd);
        this.dos.writeInt(reason);
    }

    @Override
    public void proposeAnswer(boolean accept) throws IOException {
        this.dos.writeInt(StreamBinding.proposeAnswer);
        this.dos.writeBoolean(accept);
    }
}
