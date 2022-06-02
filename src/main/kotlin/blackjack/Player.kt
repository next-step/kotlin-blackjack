package blackjack

class Player(val name: String) {
    private val cards = mutableListOf<Card>()

    fun offer(card: Card) {
        cards.add(card)
    }
}