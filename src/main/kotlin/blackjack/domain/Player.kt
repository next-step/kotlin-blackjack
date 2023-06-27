package blackjack.domain

import java.math.BigDecimal

class Player(
    name: String,
    val betAmount: BigDecimal,
    cards: Cards,
    state: ParticipantState = Alive,
) : Participant(name, cards, state) {
    override fun openedCards(): Cards = cards

    fun calculateProfit(dealer: Dealer): Profit {
        if (isBurst()) {
            return Profit(-betAmount)
        }

        val dealerScore: Score = dealer.calculateScore()
        if (dealer.isBurst()) {
            return Profit(betAmount)
        }

        val score: Score = calculateScore()
        if (score > dealerScore) {
            return Profit(betAmount)
        }

        if (score == dealerScore) {
            return Profit.ZERO
        }

        return Profit(-betAmount)
    }
}
