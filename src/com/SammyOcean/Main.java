package com.SammyOcean;

import javax.sound.midi.*;
import javax.swing.*;

public class Main {

    static JFrame jFrame = new JFrame("Music Beat");
    static DrawingPanel drawingPanel;

    public static void main(String[] args) {
	// write your code here
        Main myMain = new Main();
        myMain.start();
    }

    public void setUpGui() {
        drawingPanel = new DrawingPanel();
        jFrame.setContentPane(drawingPanel);
        jFrame.setBounds(30,30, 300,300);
        jFrame.setVisible(true);
    }

    public void start() {
        setUpGui();
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsIWant = {127};
            sequencer.addControllerEventListener(drawingPanel, eventsIWant);

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int r = 0;
            for (int i = 5; i < 61; i+= 4) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(makeMidiEvent(144, 1, r, 100, i));
                track.add(makeMidiEvent(176,1,127,0,i));
                track.add(makeMidiEvent(128, 1, r, 100, i + 2));
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (MidiUnavailableException e) {
            System.out.println("Midi Unavailable: " + e);
        } catch (InvalidMidiDataException e) {
            System.out.println("Invalid Midi Data: " + e);
        }
    }

    public static MidiEvent makeMidiEvent(int command, int channel, int note, int velocity, int tick) {
        MidiEvent midiEvent = null;
        try{
            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(command, channel, note, velocity);
            midiEvent = new MidiEvent(shortMessage, tick);
        } catch (InvalidMidiDataException e) {
            System.out.println("Invalid Midi Data: " + e);
        }
        return midiEvent;
    }
}
