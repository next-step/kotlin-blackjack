package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

data class Hit(override val cards: Cards) : State {

    override fun draw(card: Card): State {
        val addedCards = cards.add(card)

        if (addedCards.score > Score.BLACKJACK) {
            return Bust(addedCards)
        }

        if (addedCards.score < Score.BLACKJACK) {
            return Hit(addedCards)
        }

        return Blackjack(addedCards)
    }
}
