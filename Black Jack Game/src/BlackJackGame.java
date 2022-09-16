//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BlackJackGame implements Runnable, KeyListener, MouseListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    public int WIDTH = 1000;
    public int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    Card[] deck;
    public BufferStrategy bufferStrategy;
    public Image img;
    public Image background;
    public Image Start;
    public Image bet10;
    public Image bet5;
    public Image bet20;
    public Image hit;
    public Image stand;
    public Image HowToPlay;
    public Image Dealerwins;
    public Image Playerwins;
    public Image GameOver;
    public Image Cashout;
    public Image win;
    Player player;
    Player dealer;
    public int counter;
    public boolean gameover;
    public boolean nextround1;
    public boolean nextround2;
    public boolean dealerwins;
    public boolean playerwins;
    public boolean startscreen;
    public boolean startgame;
    private boolean cashOut;


    //Declare the objects used in the program
    //These are things that are made up of more than one variable type

    // Main method definition
    // This is the code that runs first and automatically
/*	public static void main(String[] args) {
		BlackJackGame ex = new BlackJackGame();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
	}
*/

    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BlackJackGame() {
        img = Toolkit.getDefaultToolkit().createImage("cards.png");
        background = Toolkit.getDefaultToolkit().createImage("background.jpg");
        Start = Toolkit.getDefaultToolkit().createImage("BlackJack.jpg");
        bet10 = Toolkit.getDefaultToolkit().createImage("bet.png");
        HowToPlay = Toolkit.getDefaultToolkit().createImage("rules.png");
        bet5 = Toolkit.getDefaultToolkit().createImage("5$.png");
        bet20 = Toolkit.getDefaultToolkit().createImage("20.png");
        hit = Toolkit.getDefaultToolkit().createImage("hitpic.png");
        stand = Toolkit.getDefaultToolkit().createImage("standpic.png");
        Playerwins = Toolkit.getDefaultToolkit().createImage("Playerwins.png");
        Dealerwins = Toolkit.getDefaultToolkit().createImage("Delarwins.png");
        GameOver = Toolkit.getDefaultToolkit().createImage("Gameover.png");
        Cashout = Toolkit.getDefaultToolkit().createImage("Cashout.png");
        win = Toolkit.getDefaultToolkit().createImage("win.png");


        setUpGraphics();
        restart();


    }

    public void restart() {


        counter = 4;
        gameover = false;
        dealerwins = false;
        playerwins = false;
        cashOut = false;
        nextround1 = false;
        nextround2 = false;
        startgame = false;
        startscreen = true;

        int count = 0;
        deck = new Card[52];
        for (int j = 4; j > 0; j--) {
            for (int k = 13; k > 0; k--) {
                deck[count] = new Card(j, k);
                count++;
            }
        }

//       //for (int q=0; q<52; q++){
//           System.out.println("Card suit: " + deck[q].suitValue + " And value: " + deck[q].cardvalue);
//       }
        for (int i = 0; i < 51; i++) {
            Card save = deck[i];
            int n = (int) (Math.random() * 52);
            deck[i] = deck[n];
            deck[n] = save;

        }
//        for (int q = 0; q < 52; q++) {
//            System.out.println("Card suit: " + deck[q].suitValue + " And value: " + deck[q].cardvalue);
//        }

        //counter =0;

        player = new Player(deck[0], deck[1]);
        dealer = new Player(deck[2], deck[3]);

        System.out.println(player.myScore);
        System.out.println(dealer.myScore);


    }

    public void nextRound() {

        counter = 4;
        gameover = false;
        dealerwins = false;
        playerwins = false;
        cashOut = false;
        nextround1 = false;
        nextround2 = false;
        startgame = true;


        int count = 0;
        deck = new Card[52];
        for (int j = 4; j > 0; j--) {
            for (int k = 13; k > 0; k--) {
                deck[count] = new Card(j, k);
                count++;
            }
        }


        for (int i = 0; i < 51; i++) {
            Card save = deck[i];
            int n = (int) (Math.random() * 52);
            deck[i] = deck[n];
            deck[n] = save;

        }

        if (player.cash > 0 || cashOut == false) {
            player.newHand(deck[0], deck[1]);
            dealer.newHand(deck[2], deck[3]);

        } else {
            player = new Player(deck[0], deck[1]);
            dealer = new Player(deck[2], deck[3]);

            System.out.println(player.myScore);
            System.out.println(dealer.myScore);
        }


    }


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //System.out.println("Youssef score is now " + youssef.myScore);
        while (true) {
            render();
            pause(20);
        }


    }


    public void moveThings() {
        //calls the move( ) code in the objects

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();


        g.drawImage(Start, 0, 0, WIDTH, HEIGHT, null);

        g.setColor(new Color(250, 250, 250));
        g.setFont(new Font("Caveat", Font.BOLD, 30));
        g.drawString("Press 'SPACEBAR' to continue", 300, 650);

        if (startscreen == false && startgame == false) {

            g.drawImage(HowToPlay, 0, 0, WIDTH, HEIGHT, null);

        }

        if (startgame == true) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            //g.clearRect(0, 0, WIDTH, HEIGHT);

            g.setColor(new Color(250, 250, 250));
            if (dealerwins == false && playerwins == false) {
                g.drawString("Current bet: " + player.bid * 2, 420, 362);
            }
            g.setColor(new Color(0, 0, 0));
            //
            g.setFont(new Font("Caveat", Font.BOLD, 30));
            g.drawString("Player Cards", 420, 475);
            g.drawString("Dealer Cards", 420, 250);
            g.drawString("Player Cash: " + player.cash, 700, 50);
            g.drawString("Player Score: " + player.myScore, 50, 50);

            if (playerwins == true || dealerwins == true) {
                g.drawString("Dealer Score: " + dealer.myScore, 50, 100);

            }

            g.drawImage(bet5, 625, 600, 100, 100, null);
            g.drawImage(bet10, 750, 600, 100, 100, null);
            g.drawImage(bet20, 875, 600, 100, 100, null);
            g.drawImage(stand, 50, 550, 100, 100, null);
            g.drawImage(hit, 175, 550, 100, 100, null);
            g.drawImage(Cashout, 350, 0, 300, 50, null);


            for (int k = 0; k < dealer.hand.length; k++) {
                if (dealer.hand[k] != null) {

                    g.drawImage(img, (k + 5) * 72, 100, (k + 6) * 72, 196, (dealer.hand[k].cardvalue - 1) * 72, (dealer.hand[k].suitValue - 1) * 96, (dealer.hand[k].cardvalue) * 72, (dealer.hand[k].suitValue) * 96, null);
                }
            }
            if (playerwins == false && dealerwins == false) {

                g.drawImage(img, 5 * 72, 100, 6 * 72, 196, 13 * 72, 96, 14 * 72, 2 * 96, null);

            }

            for (int k = 0; k < player.hand.length; k++)
                if (player.hand[k] != null) {

                    g.drawImage(img, (k + 5) * 72, 504, (k + 6) * 72, 600, (player.hand[k].cardvalue - 1) * 72, (player.hand[k].suitValue - 1) * 96, (player.hand[k].cardvalue) * 72, (player.hand[k].suitValue) * 96, null);
                }
        }

        if (nextround2 == true) {

            pause(1000);
            g.drawImage(Dealerwins, 325, 300, 400, 100, null);
        }


        if (nextround1 == true) {

            pause(1000);
            g.drawImage(Playerwins, 325, 300, 400, 100, null);
        }

        if (gameover == true) {

            pause(1000);
            g.drawImage(GameOver, 0, 0, WIDTH, HEIGHT, null);

        }

        if (cashOut == true) {
            g.drawImage(win, 0, 0, WIDTH, HEIGHT, null);

            g.setFont(new Font("Caveat", Font.BOLD, 60));
            g.setColor(new Color(250, 250, 250));
            g.drawString("You now have: $" + player.cash, 175, 362);
        }

        //draw the image of the astronaut

        g.dispose();

        bufferStrategy.show();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getX() > 750 && e.getX() < 850 && e.getY() > 600 && e.getY() < 700) {

            if (player.cash > 0) {
                player.bid = player.bid + 10;
                player.cash = player.cash - 10;
            }

            System.out.println(player.cash);

        }

        if (e.getX() > 625 && e.getX() < 725 && e.getY() > 600 && e.getY() < 700) {

            if (player.cash > 0) {
                player.bid = player.bid + 5;
                player.cash = player.cash - 5;

            }
        }

        if (e.getX() > 875 && e.getX() < 975 && e.getY() > 600 && e.getY() < 700) {

            if (player.cash > 0) {
                player.bid = player.bid + 20;
                player.cash = player.cash - 20;

            }
        }


        if ((e.getX() > 200 && e.getX() < 800 && e.getY() > 550 && e.getY() < 700) && startgame == false) {

            startgame = true;
        }

        if (e.getX() > 300 && e.getX() < 600 && e.getY() > 250 && e.getY() < 550) {

            if (playerwins == true || dealerwins == true) {


                nextRound();
                player.bid = 0;
            }
        }

        if (player.myScore < 22) {
            if (e.getX() > 175 && e.getX() < 275 && e.getY() > 550 && e.getY() < 650) {


                if (player.cardCounter < 5) {
                    player.recieve(deck[counter]);
                    counter++;
                }

            }

        }

        if (e.getX() > 50 && e.getX() < 150 && e.getY() > 550 && e.getY() < 650) {


            while (dealer.myScore < 17 && dealer.cardCounter < 5) {
                dealer.recieve(deck[counter]);
                counter++;
            }
            System.out.println("Youssef's Score: " + player.myScore);
            System.out.println("Dealer's Score: " + dealer.myScore);

            if (player.myScore > 21) {
                dealerwins = true;
                System.out.println("Dealer Wins");
            }

            if ((player.myScore > dealer.myScore || dealer.myScore > 21) && player.myScore < 22) {
                System.out.println("Youssef Wins");
                System.out.println("");
            } else {

                System.out.println("Dealer wins");
                System.out.println("");
            }

            if ((player.myScore > dealer.myScore || dealer.myScore > 21) && player.myScore < 22) {
                playerwins = true;
                nextround1 = true;
                player.cash = player.cash + (player.bid * 2);
            } else {

                dealerwins = true;
                nextround2 = true;


            }

            if (player.cash <= 0) {
                gameover = true;
            }

        }

        if (e.getX() > 350 && e.getX() < 650 && e.getY() > 0 && e.getY() < 50) {

            cashOut = true;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyChar() == ' ') {

            startscreen = false;
        }

        if (e.getKeyChar() == 'r') {

            restart();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}