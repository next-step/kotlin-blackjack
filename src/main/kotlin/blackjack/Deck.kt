package blackjack

class Deck(private val cards: MutableList<Card>) {
    fun draw(): Card {
        return cards.removeFirst()
    }
}
