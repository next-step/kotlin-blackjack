package blackjack.domain

class Player(
    name: String,
    cards: Cards,
    burst: Boolean = false,
) : Participant(name, cards, burst) {
    override fun openedCards(): Cards = cards

    fun getGameResult(dealerScore: Int): GameResult {
        if (burst) {
            return GameResult.LOSE
        }

        if (dealerScore > Cards.WINNING_NUMBER) {
            return GameResult.WIN
        }

        val score = calculateScore()
        if (score > dealerScore) {
            return GameResult.WIN
        }

        if (score == dealerScore) {
            return GameResult.TIE
        }

        return GameResult.LOSE
    }
}
