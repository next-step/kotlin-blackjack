package blackjack.domain.card

class CardHold(
    private val cards: MutableList<Card> = mutableListOf<Card>()
) {
    fun add(newCard: Card) {
        cards.add(newCard)
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }

    fun getCardsTotalSize(): Int {
        return cards.size
    }

    fun getTotalPoints(): Int {
        return cards.sumOf { card ->
            card.getPoint()
        }
    }

    companion object {
        const val BLACKJACK_CARD_POINT = 21
        const val BLACKJACK_CARD_SIZE = 2
    }
}
