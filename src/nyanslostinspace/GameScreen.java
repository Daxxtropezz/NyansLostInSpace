/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nyanslostinspace;

import java.awt.*; // uses Color, Image
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.*; // uses ImageIcon, JLabel, JOptionPane
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 *
 * @author Daxxtropezz
 */
public class GameScreen extends javax.swing.JFrame {

    /**
     * Creates new form SplashScreen
     */
    public GameScreen() {
        initComponents();

        transBg();
        RainbowLoadingBars();

        gifSupporter(nyanLoad, "extras/images/nyan.gif");
        gifSupporter(starfall, "extras/images/starfall.gif");

        gifSupporter(background, "extras/images/starfall.gif");
        gifSupporter(playme, "extras/images/playme.gif");

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 43, 43));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("icon.png")).getImage());

        mP.loadMusic(filepath);
        GameScreen.mP.clip.start();

        playBtn.setVisible(false);
        gamePanel.setVisible(false);
        charTarget.setVisible(false);

        charSelector(characters, charSelect, 7);

        UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.BOLD, 13));
        UIManager.put("OptionPane.background", new Color(0, 76, 147));
        UIManager.put("OptionPane.contentMargins", new Insets(20, 20, 20, 20));
        UIManager.put("OptionPane.setButtonMargin", false);
        UIManager.put("OptionPane.okButtonText", "🐾");

        UIManager.put("OptionPane.errorIcon", icon);
        UIManager.put("ToolTip.background", Color.black);
        UIManager.put("ToolTip.foreground", Color.white);
        UIManager.put("ToolTip.font", new Font("Monospaced", Font.BOLD, 13));
    }

    ImageIcon nyanLOGO = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("icon.png")).getImage());
    Image nyanIMG = nyanLOGO.getImage();
    Image scaledNyan = nyanIMG.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    ImageIcon iconifiedNyan = new ImageIcon(scaledNyan);
    ImageIcon icon = iconifiedNyan;

    int nyanLives = 8;
    int speed = 1;
    int explosionLife = 3;
    public static int timer = 30;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    //creates global music player system
    public static mediaPlay mP = new mediaPlay();
    String music = "extras/audios/Nyanyan.wav";
    URL filepath = getClass().getResource(music);

    String gameOverSound = "extras/audios/GameOver.wav";
    URL gO = getClass().getResource(gameOverSound);

    public static long clipTimePosition;

    public static boolean isPlaying = true;

    //start of mainMenu
    int i = 7;
    int xMouse;
    int yMouse;
    public static boolean nyanPlay;

    String[] quotes = {
        "In a nyan's eye, all things \nbelong to nyans. ",
        "There's nothing like watching \nnyan videos to help me pass the \ntime until I can see my nyan again. ",
        "Nyan's motto: \"No matter what \nyou've done wrong, always try \nto make it look as if the dog did it.\" ",
        "These aren't my thoughts, \nthey're my nyan walking \non the keyboard. ",
        "A nyan sneezing is a \ngood omen for everyone \nwho hears it. ",
        "The ability to bath \na nyan is a martial art. ",
        "For a man to truly \nunderstand rejection, he must \nfirst be ignored by a nyan. ",
        "One nyan just leads to another. ",
        "Never try to out-stubborn a nyan. ",
        "Everything comes to those who wait... \nexcept for nyans. ",
        "Look what the nyans dragged in. ",
        "I think nyans are fantastic. ",
        "We're all mad here. \n\t-The Nyans ",
        "Never work with hoomans or animals. ",
        "A nyan is never vulgar. ",
        "All things can be forgiven \nif we can progress. ",
        "I'm a cool nyan, \nbut I'm on life nine. \n\t-Lil' Nyan ",
        "A nyan can be your friend, but never your slave. "
    };
    Random rand = new Random();

    public String[] characters
            = {
                "extras/images/characters/angel-nyan.gif",
                "extras/images/characters/coin-nyan.gif",
                "extras/images/characters/console-nyan.gif",
                "extras/images/characters/cool-nyan.gif",
                "extras/images/characters/dough-nyan.gif",
                "extras/images/characters/dubstep-nyan.gif",
                "extras/images/characters/fiesta-dog-nyan.gif",
                "extras/images/characters/default-nyan.gif",
                "extras/images/characters/galaxy-nyan.gif",
                "extras/images/characters/gift-nyan.gif",
                "extras/images/characters/pika-nyan.gif",
                "extras/images/characters/pirate-nyan.gif",
                "extras/images/characters/squi-nyan.gif",
                "extras/images/characters/toasted-nyan.gif",
                "extras/images/characters/toaster-nyan.gif",
                "extras/images/characters/zoro-nyan.gif",
                "extras/images/characters/anya-nyan.gif"
            };
    public String[] nyanNames = {
        "Angel Nyan",
        "Coin Nyan",
        "Console Nyan",
        "Cool Nyan",
        "Donut Nyan",
        "Dubstep Nyan",
        "Fiesta Dog Nyan",
        "Nyan",
        "Galaxy Nyan",
        "Yuletide Nyan",
        "Pika-Nyan",
        "Pirate Nyan",
        "Squirrel Nyan",
        "Toasted Nyan",
        "Toaster Nyan",
        "Zoro?!",
        "Anya",};

    public void charSelector(String[] link, JLabel label, int select) {

        ImageIcon iconLOGO1 = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(link[select])).getImage());
        Image imgLOGO1 = iconLOGO1.getImage();
        Image imgScaleLOGO1 = imgLOGO1.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon scaledIconLOGO1 = new ImageIcon(imgScaleLOGO1);
        label.setIcon(scaledIconLOGO1);

        nyanCharNames.setText(nyanNames[select]);

        int max = link.length - 1;

        if (select <= 0) {
            charPrev.setVisible(false);
            charNext.setVisible(true);
        } else if (select >= max) {
            charPrev.setVisible(true);
            charNext.setVisible(false);
        }

        if (select > 0) {
            charPrev.setVisible(true);
        }
        if (select < max) {
            charNext.setVisible(true);
        }
    }
    //end of mainMenu

    public void gifSupporter(JLabel label, String path) {
        ImageIcon iconLOGO = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(path)).getImage());
        Image imgLOGO = iconLOGO.getImage();
        Image imgScaleLOGO = imgLOGO.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon scaledIconLOGO = new ImageIcon(imgScaleLOGO);
        label.setIcon(scaledIconLOGO);
    }

    public void transBg() {
        setBackground(new Color(0, 0, 0, 0));
    }

    public void RainbowLoadingBars() {
        REDloadingBar.setUI(new BasicProgressBarUI() {
        });
        REDloadingBar.setStringPainted(true);
        REDloadingBar.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        ORAloadingBar.setUI(new BasicProgressBarUI() {
        });
        ORAloadingBar.setStringPainted(true);
        ORAloadingBar.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        YELloadingBar.setUI(new BasicProgressBarUI() {
        });
        YELloadingBar.setStringPainted(true);
        YELloadingBar.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        GREloadingBar.setUI(new BasicProgressBarUI() {
        });
        GREloadingBar.setStringPainted(true);
        GREloadingBar.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        BLUloadingBar.setUI(new BasicProgressBarUI() {
        });
        BLUloadingBar.setStringPainted(true);
        BLUloadingBar.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

        VIOloadingBar.setUI(new BasicProgressBarUI() {
        });
        VIOloadingBar.setStringPainted(true);
        VIOloadingBar.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        charTarget = new javax.swing.JLabel();
        SplashPanel = new javax.swing.JPanel();
        playBtn = new javax.swing.JButton();
        movable = new javax.swing.JLabel();
        REDloadingBar = new javax.swing.JProgressBar();
        ORAloadingBar = new javax.swing.JProgressBar();
        YELloadingBar = new javax.swing.JProgressBar();
        GREloadingBar = new javax.swing.JProgressBar();
        BLUloadingBar = new javax.swing.JProgressBar();
        VIOloadingBar = new javax.swing.JProgressBar();
        nyanLoad = new javax.swing.JLabel();
        starfall = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        charReload = new javax.swing.JButton();
        mediaPlay = new javax.swing.JButton();
        aboutNyan = new javax.swing.JButton();
        charPrev = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        charNext = new javax.swing.JButton();
        minNyan = new javax.swing.JButton();
        nyanCharNames = new javax.swing.JLabel();
        charSelect = new javax.swing.JLabel();
        playme = new javax.swing.JLabel();
        winMover = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nyans: Lost In Space");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        charTarget.setFont(new java.awt.Font("Monospaced", 1, 55)); // NOI18N
        charTarget.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charTarget.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        charTarget.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        charTarget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                charTargetMousePressed(evt);
            }
        });
        getContentPane().add(charTarget, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 450, 230));

        SplashPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playBtn.setBackground(new java.awt.Color(153, 102, 255));
        playBtn.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        playBtn.setText("Play");
        playBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playBtnMouseExited(evt);
            }
        });
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });
        SplashPanel.add(playBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 150, 60));

        movable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                movableMouseDragged(evt);
            }
        });
        movable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                movableMousePressed(evt);
            }
        });
        SplashPanel.add(movable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 330));

        REDloadingBar.setBackground(new java.awt.Color(255, 255, 255));
        REDloadingBar.setFont(new java.awt.Font("Mistral", 0, 14)); // NOI18N
        REDloadingBar.setForeground(new java.awt.Color(255, 0, 0));
        REDloadingBar.setBorder(null);
        REDloadingBar.setString("");
        SplashPanel.add(REDloadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 730, 10));

        ORAloadingBar.setBackground(new java.awt.Color(255, 255, 255));
        ORAloadingBar.setFont(new java.awt.Font("Mistral", 0, 14)); // NOI18N
        ORAloadingBar.setForeground(new java.awt.Color(255, 102, 0));
        ORAloadingBar.setBorder(null);
        ORAloadingBar.setString("");
        SplashPanel.add(ORAloadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 730, 10));

        YELloadingBar.setBackground(new java.awt.Color(255, 255, 255));
        YELloadingBar.setFont(new java.awt.Font("Mistral", 0, 14)); // NOI18N
        YELloadingBar.setForeground(new java.awt.Color(255, 255, 51));
        YELloadingBar.setBorder(null);
        YELloadingBar.setString("");
        SplashPanel.add(YELloadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 730, 10));

        GREloadingBar.setBackground(new java.awt.Color(255, 255, 255));
        GREloadingBar.setFont(new java.awt.Font("Mistral", 0, 14)); // NOI18N
        GREloadingBar.setForeground(new java.awt.Color(0, 255, 51));
        GREloadingBar.setBorder(null);
        GREloadingBar.setString("");
        SplashPanel.add(GREloadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 730, 10));

        BLUloadingBar.setBackground(new java.awt.Color(255, 255, 255));
        BLUloadingBar.setFont(new java.awt.Font("Mistral", 0, 14)); // NOI18N
        BLUloadingBar.setForeground(new java.awt.Color(0, 102, 255));
        BLUloadingBar.setBorder(null);
        BLUloadingBar.setString("");
        SplashPanel.add(BLUloadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 730, 10));

        VIOloadingBar.setBackground(new java.awt.Color(255, 255, 255));
        VIOloadingBar.setFont(new java.awt.Font("Mistral", 0, 14)); // NOI18N
        VIOloadingBar.setForeground(new java.awt.Color(153, 102, 255));
        VIOloadingBar.setBorder(null);
        VIOloadingBar.setString("");
        SplashPanel.add(VIOloadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 730, 10));

        nyanLoad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SplashPanel.add(nyanLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 375, 240));

        starfall.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        starfall.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        SplashPanel.add(starfall, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 330));

        getContentPane().add(SplashPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 330));

        gamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        charReload.setBackground(new java.awt.Color(153, 102, 255));
        charReload.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        charReload.setText("🔄");
        charReload.setToolTipText("reload me nyan!");
        charReload.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        charReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        charReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                charReloadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                charReloadMouseExited(evt);
            }
        });
        charReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                charReloadActionPerformed(evt);
            }
        });
        gamePanel.add(charReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 50, 50));

        mediaPlay.setBackground(new java.awt.Color(153, 102, 255));
        mediaPlay.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        mediaPlay.setText("🔊");
        mediaPlay.setToolTipText("audio nyan");
        mediaPlay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        mediaPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mediaPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mediaPlayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mediaPlayMouseExited(evt);
            }
        });
        mediaPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaPlayActionPerformed(evt);
            }
        });
        gamePanel.add(mediaPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 50, 50));

        aboutNyan.setBackground(new java.awt.Color(153, 102, 255));
        aboutNyan.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        aboutNyan.setText("ⓘ");
        aboutNyan.setToolTipText("nyann of wisdom");
        aboutNyan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        aboutNyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aboutNyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aboutNyanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aboutNyanMouseExited(evt);
            }
        });
        aboutNyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutNyanActionPerformed(evt);
            }
        });
        gamePanel.add(aboutNyan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 50, 50));

        charPrev.setBackground(new java.awt.Color(153, 102, 255));
        charPrev.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        charPrev.setText("◄");
        charPrev.setToolTipText("previous nyan");
        charPrev.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        charPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        charPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                charPrevMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                charPrevMouseExited(evt);
            }
        });
        charPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                charPrevActionPerformed(evt);
            }
        });
        gamePanel.add(charPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 70, 50));

        closeBtn.setBackground(new java.awt.Color(153, 102, 255));
        closeBtn.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        closeBtn.setText("✖");
        closeBtn.setToolTipText("close nyan");
        closeBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        closeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeBtnMouseExited(evt);
            }
        });
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gamePanel.add(closeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 50, 30));

        charNext.setBackground(new java.awt.Color(153, 102, 255));
        charNext.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        charNext.setText("►");
        charNext.setToolTipText("next nyan");
        charNext.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        charNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        charNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                charNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                charNextMouseExited(evt);
            }
        });
        charNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                charNextActionPerformed(evt);
            }
        });
        gamePanel.add(charNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 70, 50));

        minNyan.setBackground(new java.awt.Color(153, 102, 255));
        minNyan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        minNyan.setText("🗕");
        minNyan.setToolTipText("minimize nyan");
        minNyan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 0), new java.awt.Color(255, 0, 204)));
        minNyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minNyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minNyanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minNyanMouseExited(evt);
            }
        });
        minNyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minNyanActionPerformed(evt);
            }
        });
        gamePanel.add(minNyan, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 50, 30));

        nyanCharNames.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        nyanCharNames.setForeground(new java.awt.Color(153, 153, 255));
        nyanCharNames.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gamePanel.add(nyanCharNames, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 460, 60));

        charSelect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        charSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                charSelectMouseClicked(evt);
            }
        });
        gamePanel.add(charSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 450, 230));

        playme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gamePanel.add(playme, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 150, 105));

        winMover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winMover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                winMoverMouseDragged(evt);
            }
        });
        winMover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                winMoverMousePressed(evt);
            }
        });
        gamePanel.add(winMover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 330));

        timeLabel.setFont(new java.awt.Font("Monospaced", 1, 55)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(153, 102, 255));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("30");
        timeLabel.setToolTipText("time left to start");
        gamePanel.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, -1));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        gamePanel.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 330));

        getContentPane().add(gamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 330));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void movableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movableMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_movableMousePressed

    private void movableMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movableMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_movableMouseDragged

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        SplashPanel.setVisible(false);
        gamePanel.setVisible(true);
    }//GEN-LAST:event_playBtnActionPerformed

    private void playBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playBtnMouseEntered
        playBtn.setForeground(Color.white);
    }//GEN-LAST:event_playBtnMouseEntered

    private void playBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playBtnMouseExited
        playBtn.setForeground(Color.black);
    }//GEN-LAST:event_playBtnMouseExited

    private void charReloadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charReloadMouseEntered
        charReload.setForeground(Color.WHITE);
        charReload.setText("😻");
    }//GEN-LAST:event_charReloadMouseEntered

    private void charReloadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charReloadMouseExited
        charReload.setForeground(Color.BLACK);
        charReload.setText("🔄");
    }//GEN-LAST:event_charReloadMouseExited

    private void charReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_charReloadActionPerformed
        i = 7;
        charSelector(characters, charSelect, i);
    }//GEN-LAST:event_charReloadActionPerformed

    private void mediaPlayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mediaPlayMouseEntered
        mediaPlay.setForeground(Color.WHITE);
    }//GEN-LAST:event_mediaPlayMouseEntered

    private void mediaPlayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mediaPlayMouseExited
        mediaPlay.setForeground(Color.BLACK);
    }//GEN-LAST:event_mediaPlayMouseExited

    private void mediaPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaPlayActionPerformed
        if (GameScreen.isPlaying) {
            GameScreen.clipTimePosition = GameScreen.mP.clip.getMicrosecondPosition();
            GameScreen.mP.clip.stop();
            mediaPlay.setText("🔇");
            mediaPlay.setToolTipText("unmute");
            GameScreen.isPlaying = false;
        } else {
            GameScreen.mP.clip.setMicrosecondPosition(GameScreen.clipTimePosition);
            GameScreen.mP.clip.start();
            GameScreen.mP.clip.loop(Clip.LOOP_CONTINUOUSLY);
            mediaPlay.setText("🔊");
            mediaPlay.setToolTipText("mute");
            GameScreen.isPlaying = true;
        }
    }//GEN-LAST:event_mediaPlayActionPerformed

    private void aboutNyanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutNyanMouseEntered
        aboutNyan.setForeground(Color.WHITE);
    }//GEN-LAST:event_aboutNyanMouseEntered

    private void aboutNyanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutNyanMouseExited
        aboutNyan.setForeground(Color.BLACK);
    }//GEN-LAST:event_aboutNyanMouseExited

    private void aboutNyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutNyanActionPerformed
        int value = rand.nextInt(quotes.length);

        JOptionPane.showMessageDialog(this, quotes[value], "😻 Nyans of Wisdom", JOptionPane.PLAIN_MESSAGE, icon);
    }//GEN-LAST:event_aboutNyanActionPerformed

    private void charPrevMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charPrevMouseEntered
        charPrev.setForeground(Color.white);
    }//GEN-LAST:event_charPrevMouseEntered

    private void charPrevMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charPrevMouseExited
        charPrev.setForeground(Color.black);
    }//GEN-LAST:event_charPrevMouseExited

    private void charPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_charPrevActionPerformed
        i--;
        charSelector(characters, charSelect, i);
    }//GEN-LAST:event_charPrevActionPerformed

    private void closeBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBtnMouseEntered
        closeBtn.setForeground(Color.red);
    }//GEN-LAST:event_closeBtnMouseEntered

    private void closeBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBtnMouseExited
        closeBtn.setForeground(Color.black);
    }//GEN-LAST:event_closeBtnMouseExited

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Are you sure to exit?\nNyans will gonna be sad. 😿 ", "🙀 Nyan Depression",
                JOptionPane.YES_NO_OPTION, 0, icon) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_closeBtnActionPerformed

    private void charNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charNextMouseEntered
        charNext.setForeground(Color.white);
    }//GEN-LAST:event_charNextMouseEntered

    private void charNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charNextMouseExited
        charNext.setForeground(Color.black);
    }//GEN-LAST:event_charNextMouseExited

    private void charNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_charNextActionPerformed
        i++;
        charSelector(characters, charSelect, i);
    }//GEN-LAST:event_charNextActionPerformed

    private void minNyanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minNyanMouseEntered
        minNyan.setForeground(Color.white);
    }//GEN-LAST:event_minNyanMouseEntered

    private void minNyanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minNyanMouseExited
        minNyan.setForeground(Color.black);
    }//GEN-LAST:event_minNyanMouseExited

    private void minNyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minNyanActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minNyanActionPerformed

    private void charSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charSelectMouseClicked
        String passer = characters[i];

        ImageIcon iconLOGO = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(passer)).getImage());
        Image imgLOGO = iconLOGO.getImage();
        Image imgScaleLOGO = imgLOGO.getScaledInstance(charSelect.getWidth(), charSelect.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon IconPass = new ImageIcon(imgScaleLOGO);
        charTarget.setIcon(IconPass);

        gamePanel.setVisible(false);
        charTarget.setVisible(true);
    }//GEN-LAST:event_charSelectMouseClicked

    private void winMoverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_winMoverMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_winMoverMouseDragged

    private void winMoverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_winMoverMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_winMoverMousePressed

    private void charTargetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_charTargetMousePressed
        nyanLives--;
        speed += 2;
        if (nyanLives == -1) {
            mP.clip.stop();
            mP.loadMusic(gO);
            mP.clip.start();
            ImageIcon iconLOGO = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("extras/images/explosion.gif")).getImage());
            Image imgLOGO = iconLOGO.getImage();
            Image imgScaleLOGO = imgLOGO.getScaledInstance(charTarget.getWidth(), charTarget.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon IconPass = new ImageIcon(imgScaleLOGO);
            charTarget.setIcon(IconPass);
        }
    }//GEN-LAST:event_charTargetMousePressed

    public void run() throws Exception {

        //start location
        int y = this.getY();
        int x = this.getX();
        boolean yb = false;
        boolean xb = false;

        //loop for the steps
        while (nyanLives >= 0) {
            Thread.sleep(10);
            setLocation(x, y);

            // direction
            if (y >= dim.getHeight() - 200) {
                yb = true;
            } else if (y <= -200) {
                yb = false;
            }

            if (x >= dim.getWidth() - 200) {
                xb = true;
            } else if (x <= -200) {
                xb = false;
            }

            if (yb) {
                y -= speed;
            } else {
                y += speed;
            }

            if (xb) {
                x -= speed;
            } else {
                x += speed;
            }

        }
        //wait for 1 second
        Thread.sleep(1000);
        mP.loadMusic(filepath);
        GameScreen.mP.clip.start();
        timer = 30;
        nyanLives = 7;
        charTarget.setVisible(false);
        gamePanel.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        GameScreen frame = new GameScreen();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        try {

            for (int loading = 0; loading <= 100; loading++) {
                if (loading <= 10) {
                    Thread.sleep((long) (Math.random() * 150));
                } else if (loading <= 20) {
                    Thread.sleep((long) (Math.random() * 150));
                } else if (loading <= 50) {
                    Thread.sleep((long) (Math.random() * 100));
                } else if (loading <= 75) {
                    Thread.sleep((long) (Math.random() * 100));
                } else if (loading <= 80) {
                    Thread.sleep((long) (Math.random() * 100));
                    loading = loading + 2;
                } else if (loading <= 90) {
                    Thread.sleep((long) (Math.random() * 150));
                } else if (loading == 100) {
                    frame.showBtn();
                    for (; timer >= 0; timer--) {
                        Thread.sleep(1000);
                        timeLabel.setText("" + timer + "");
                        if (charTarget.isVisible()) {
                            frame.run();
                        }
                        if (timer == 0) {
                            JOptionPane.showMessageDialog(frame, "The Game Will Now Exit! ", "😼 NYANgry nyan!", 0);
                            System.exit(0);
                        } else if (timer <= 15) {
                            for (int colored = 0; colored <= 2; colored++) {
                                Thread.sleep(50);
                                timeLabel.setForeground(Color.white);
                                Thread.sleep(50);
                                timeLabel.setForeground(Color.red);
                            }
                        }
                    }
                }

                frame.REDloadingBar.setValue(loading);
                frame.ORAloadingBar.setValue(loading);
                frame.YELloadingBar.setValue(loading);
                frame.GREloadingBar.setValue(loading);
                frame.BLUloadingBar.setValue(loading);
                frame.VIOloadingBar.setValue(loading);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void showBtn() {
        playBtn.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar BLUloadingBar;
    private javax.swing.JProgressBar GREloadingBar;
    private javax.swing.JProgressBar ORAloadingBar;
    private javax.swing.JProgressBar REDloadingBar;
    public static javax.swing.JPanel SplashPanel;
    private javax.swing.JProgressBar VIOloadingBar;
    private javax.swing.JProgressBar YELloadingBar;
    private javax.swing.JButton aboutNyan;
    private javax.swing.JLabel background;
    private javax.swing.JButton charNext;
    private javax.swing.JButton charPrev;
    private javax.swing.JButton charReload;
    public static javax.swing.JLabel charSelect;
    public static javax.swing.JLabel charTarget;
    private javax.swing.JButton closeBtn;
    public static javax.swing.JPanel gamePanel;
    private javax.swing.JButton mediaPlay;
    private javax.swing.JButton minNyan;
    private javax.swing.JLabel movable;
    private javax.swing.JLabel nyanCharNames;
    private javax.swing.JLabel nyanLoad;
    public static javax.swing.JButton playBtn;
    private javax.swing.JLabel playme;
    private javax.swing.JLabel starfall;
    public static javax.swing.JLabel timeLabel;
    private javax.swing.JLabel winMover;
    // End of variables declaration//GEN-END:variables
}
