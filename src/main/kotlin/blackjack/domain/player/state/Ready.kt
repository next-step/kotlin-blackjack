package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.player.state.hands.Hands

data class Ready(override val hands: Hands = Hands.EMPTY) : Running(hands) {

    override fun draw(card: Card): PlayerState {
        val hands = hands.draw(card)
        return when {
            hands.isReady() -> Ready(hands)
            hands.isBlackJack() -> BlackJack(hands)
            else -> Hit(hands)
        }
    }
}
