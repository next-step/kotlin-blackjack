package blackjack.domain.participant

import blackjack.domain.card.Cards
import blackjack.domain.game.BlackJack
import blackjack.domain.game.Rank

class Player(name: String, cards: Cards = Cards(), private var bettingAmount: Int = 0) : Participant(name, cards) {

    private var isTurnFinished: Boolean = false

    fun finishedTurn() {
        isTurnFinished = true
    }

    fun canProceedTurn() = !isTurnFinished && score() <= BlackJack.BLACKJACK_MAX_SCORE

    fun bet(amount: Int) {
        this.bettingAmount = amount
    }

    fun getRevenue(dealer: Dealer): Int {
        return Rank.of(this, dealer).calculateRevenue(bettingAmount)
    }
}
