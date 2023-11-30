package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.Card

class Player(
    val name: PlayerName,
    val cards: PlayerCards = PlayerCards()
) {

    fun handCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return cards.isBust()
    }

    fun calculateScore(): Score {
        return cards.calculateScore()
    }
}
