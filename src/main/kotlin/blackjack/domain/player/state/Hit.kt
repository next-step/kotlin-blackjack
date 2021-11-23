package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.player.state.hands.Hands

data class Hit(override val hands: Hands) : Running(hands) {

    override fun draw(card: Card): PlayerState {
        val hands = hands.draw(card)
        if (hands.isBust()) {
            return Bust(hands)
        }
        return Hit(hands)
    }
}
