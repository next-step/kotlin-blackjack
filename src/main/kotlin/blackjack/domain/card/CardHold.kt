package blackjack.domain.card

class CardHold(
    val cards: List<Card> = emptyList()
) {
    fun add(newCard: Card): CardHold {
        val addedCards = cards + newCard
        return CardHold(addedCards)
    }

    fun getCardsTotalSize(): Int {
        return cards.size
    }

    fun getTotalPoints(): Int {
        return cards.sumOf { card ->
            card.getPoint()
        }
    }
}
