package blackjack.step2.domain

class Cards(private val cards: MutableList<Card>) {
    val all: List<Card>
        get() = cards.toList()

    fun add(card: Card): Cards {
        cards.add(card)
        return this
    }

    companion object {
        fun of(cards: List<Card>): Cards {
            return Cards(cards.toMutableList())
        }
    }
}
