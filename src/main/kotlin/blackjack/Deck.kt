package blackjack

data class Deck(private val cards: List<Card>) {

    val size: Int = cards.size

    fun isEmpty(): Boolean = size == 0
}
