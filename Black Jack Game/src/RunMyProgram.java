public class RunMyProgram {
    public static void main(String[] args) {
        BlackJackGame ex = new BlackJackGame();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }
}
