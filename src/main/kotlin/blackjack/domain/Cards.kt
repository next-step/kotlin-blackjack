package blackjack.domain

class Cards(
    private val _cards: List<Card> = emptyList()
) {

    val cards = _cards.toMutableList()
    fun get(index: Int): Card {
        return cards[index]
    }

    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        fun of(cards: List<Card>): Cards {
            return Cards(cards.shuffled())
        }
    }
}

private fun List<Card>.toCards() = Cards(this)
