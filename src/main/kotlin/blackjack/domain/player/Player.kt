package blackjack.domain.player

import blackjack.domain.card.Cards

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    fun isWin(dealer: Dealer): Boolean {
        if (cards.getScore().isBust()) {
            return false
        }

        if (dealer.cards.getScore().isBust()) {
            return true
        }

        return cards.getScore().value > dealer.cards.getScore().value
    }

    override fun canReceive(): Boolean {
        return !cards.getScore().isBust()
    }
}
