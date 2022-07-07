package blackjack.domain.participant.state

import blackjack.domain.deck.Card

class Hit(cards: Cards) : Running(cards) {
    override fun receiveCard(card: Card): State {
        val cards = this.cards.receiveCard(card = card)

        return when (cards.isBustScore) {
            true -> Bust(cards)
            false -> Hit(cards)
        }
    }

    override fun stay(): State = Stay(this.cards)
}
