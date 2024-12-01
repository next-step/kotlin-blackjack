package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.score.Score

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    fun isWin(dealer: Dealer): Boolean {
        if (Score(dealer.cards.getScore()).isBust()) {
            return true
        }

        if (Score(cards.getScore()).isBust()) {
            return false
        }

        return cards.getScore() > dealer.cards.getScore()
    }

    override fun canReceive(): Boolean {
        return !Score(cards.getScore()).isBust()
    }
}
