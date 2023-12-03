package blackjack.domain.player

import blackjack.domain.ParticipantName
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(
    val name: ParticipantName,
    val cards: Cards = Cards()
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
