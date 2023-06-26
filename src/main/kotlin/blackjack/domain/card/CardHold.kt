package blackjack.domain.card

class CardHold(
    val cards: MutableList<Card> = mutableListOf<Card>()
) {
    fun add(newCard: Card) {
        cards.add(newCard)
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
