package blackjack.domain

import java.math.BigDecimal

class Player(
    name: String,
    val betAmount: BigDecimal,
    cards: Cards,
    state: ParticipantState = Alive,
) : Participant(name, cards, state) {
    override fun openedCards(): Cards = cards

    fun getGameResult(dealer: Dealer): GameResult {
        if (isBurst()) {
            return GameResult.LOSE
        }

        val dealerScore: Score = dealer.calculateScore()
        if (dealer.isBurst()) {
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
