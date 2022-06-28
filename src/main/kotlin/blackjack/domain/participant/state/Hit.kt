package blackjack.domain.participant.state

import blackjack.domain.deck.Card

class Hit(hand: Hand) : Running(hand) {
    override fun receiveCard(card: Card): State {
        this.hand.receiveCard(card = card)

        return when (this.hand.isBust()) {
            true -> Bust(this.hand)
            false -> Hit(this.hand)
        }
    }

    override fun stay(): State {
        TODO("Not yet implemented")
    }
}
