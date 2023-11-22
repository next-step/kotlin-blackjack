package blackJack.domain

import blackJack.domain.Card.Companion.drawCard
import blackJack.error.ErrorMessage

class Cards(val cards: List<Card>) {
    init {
        require(cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
        require(cards.distinct().size == cards.size) { ErrorMessage.DUPLICATE_CARDS.message }
    }

    fun drawUniqueCard(): Card {
        var newCard = drawCard()
        while (isDuplicate(newCard)) {
            newCard = drawCard()
        }

        return newCard
    }

    private fun isDuplicate(newCard: Card) = cards.any { it == newCard }
}
