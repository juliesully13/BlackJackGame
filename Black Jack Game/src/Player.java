public class Player {

    public int cardCounter;
    Card[] hand;
    int myScore;
    int bid;
    int cash;

    public Player(Card c, Card c2) {

        hand = new Card[5];
        hand[0] = c;
        hand[1] = c2;
        cardCounter = 2;
        bid = 0;
        cash = 500;

        myScore = processhand();


    }

    public void recieve(Card anythingelse) {


        hand[cardCounter] = anythingelse;
        System.out.println(myScore);
        myScore = processhand();
        System.out.println(myScore);
        cardCounter++;

    }

    public int processhand() {

        int aceCounter = 0;
        int scorepH = 0;

        for (int k = 0; k < hand.length; k++) {
            if (hand[k] != null) {
                if (hand[k].cardvalue == 1) {
                    aceCounter++;
                    scorepH = scorepH + 11;
                } else {
                    scorepH = scorepH + hand[k].realcardvalue;
                }

            }
        }
        while (scorepH > 21 && aceCounter > 0) {
            scorepH = scorepH - 10;
            aceCounter--;
        }
        return scorepH;
    }

    public void newHand(Card card, Card card1) {
        hand = new Card[5];
        hand[0] = card;
        hand[1] = card1;
        cardCounter = 2;
        myScore = processhand();
    }
}


