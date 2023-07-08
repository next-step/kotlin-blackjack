package blackjack.domain


enum class GameResult {
    WIN,
    LOSS,
    DRAW;

    private fun opposite(): GameResult {
        return when {
            this == WIN -> LOSS
            this == LOSS -> WIN
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
                dealerScore > BLACK_JACK -> LOSS
                dealerScore > playerScore -> LOSS
                dealerScore < playerScore -> WIN
                else -> DRAW
            }
        }
    }
}