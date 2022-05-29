package blackjack.domain

class Deck(private val cards: List<Card>) {
    val count: Int
        get() = cards.size

    fun draw(): Card {
        return Card(Suite.HEARTS, Denomination.TEN)
    }
}
