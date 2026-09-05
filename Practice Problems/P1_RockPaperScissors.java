public class P1_RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        java.util.Random random = new java.util.Random();

        int wins = 0, losses = 0, draws = 0;
        int rounds = playerMoves.length;

        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < rounds; i++) {
            String computerMove = moves[random.nextInt(moves.length)];
            String result = playRound(playerMoves[i], computerMove);
            System.out.println((i + 1) + " | " + playerMoves[i] + " | " + computerMove + " | " + result);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;
        }

        double winPercent = (wins * 100.0) / rounds;
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws + " | Win % = " + winPercent + "%");
    }
}
