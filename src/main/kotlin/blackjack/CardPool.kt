package blackjack

import blackjack.card.CardNumber
import blackjack.card.CardType

class CardPool {
    private val _cards = cardGenerate()
    val cards: Set<Card>
        get() = _cards

    private fun cardGenerate() = CardType.values().flatMap(::generateCardType).shuffled().toSet()

    private fun generateCardType(cardType: CardType) = CardNumber.values().map { Card.newInstance(cardType, it) }
}
