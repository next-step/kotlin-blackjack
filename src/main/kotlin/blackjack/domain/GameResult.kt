package blackjack.domain

enum class GameResult(
    val message: String
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private fun opposite(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }

    companion object {
        fun resultOfDealer(players: Players, dealer: Dealer): List<GameResult> {
            return players.map{
                GameResult.resultOfPlayer(it, dealer).opposite()
            }
        }

        fun resultOfPlayer(player: Player, dealer: Dealer): GameResult {
            val playerScore = player.cards.toScore()
            val dealerScore = dealer.cards.toScore()
            return when {
                dealerScore > Score.BLACKJACK || playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
