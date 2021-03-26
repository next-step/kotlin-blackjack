package blackjack.domain.card.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

object StateFactory {
    fun create(firstCard: Card, secondCard: Card): State {
        val cards = Cards(firstCard, secondCard)
        if (cards.score > BlackJack.SCORE) {
            return Bust(cards)
        }
        if (cards.score == BlackJack.SCORE) {
            return BlackJack(cards)
        }
        return Hit(cards)
    }
}
