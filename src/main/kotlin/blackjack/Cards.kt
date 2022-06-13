package blackjack

class Cards(private val values: List<Card> = listOf()) {
    fun add(card: Card): Cards {
        return Cards(this.values.plus(card))
    }

    fun size(): Int {
        return this.values.size
    }
}
