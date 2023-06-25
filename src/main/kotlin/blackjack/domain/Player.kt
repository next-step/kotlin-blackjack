package blackjack.domain

class Player(
    name: String,
    cards: Cards,
    burst: Boolean = false,
) : Participant(name, cards, burst) {
    override fun openedCards(): Cards = cards

    fun getGameResult(dealer: Dealer): GameResult {
        if (burst) {
            return GameResult.LOSE
        }

        val dealerScore: Score = dealer.calculateScore()
        if (dealer.burst) {
            return GameResult.WIN
        }

        val score: Score = calculateScore()
        if (score > dealerScore) {
            return GameResult.WIN
        }

        if (score == dealerScore) {
            return GameResult.TIE
        }

        return GameResult.LOSE
    }
}
