package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

data class Initial(
    override val cards: Cards = Cards.from(emptyList())
) : Running(cards) {

    override fun draw(card: Card): State {
        val drawnCards = cards + card
        return when {
            drawnCards.isBlackjack -> {
                Blackjack(drawnCards)
            }
            drawnCards.isInitial -> {
                Initial(drawnCards)
            }
            else -> {
                Hit(drawnCards)
            }
        }
    }
}
