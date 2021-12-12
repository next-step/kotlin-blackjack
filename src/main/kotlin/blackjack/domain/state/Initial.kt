package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

data class Initial(
    override val cards: Cards = Cards.from(emptyList())
) : Running(cards) {

    override fun draw(card: Card): State {
        val cards = cards + card
        return when {
            cards.isBlackjack -> {
                Blackjack(cards)
            }
            cards.isInitial -> {
                Initial(cards)
            }
            else -> {
                Hit(cards)
            }
        }
    }
}
