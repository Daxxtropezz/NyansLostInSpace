/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nyanslostinspace;

import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Daxxtropezz
 */
public class mediaPlay {

    Clip clip;

    public void loadMusic(URL filepath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);

        } catch (Exception reject) {
            JOptionPane.showMessageDialog(null, reject);
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

}
