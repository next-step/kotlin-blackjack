package blackjack.domain

enum class GameResult {
    WIN,
    LOSE,
    DRAW;

    private fun opposite(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }

    companion object {
        fun resultOfDealer(players: Players, dealer: Dealer): List<GameResult> {
            return players.gameResults(dealer)
                .map { it.opposite() }
        }

        fun resultOfPlayer(player: Player, dealer: Dealer): GameResult {
            val playerScore = player.score
            val dealerScore = dealer.score
            return when {
                playerScore == BLACK_JACK && dealerScore != BLACK_JACK -> WIN
                dealerScore > BLACK_JACK || dealerScore < playerScore -> WIN
                dealerScore > playerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
