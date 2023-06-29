package blackjack

class Player {

    private val cardPocket: MutableList<Card> = mutableListOf()

    val currentCards: List<Card>
        get() = cardPocket

    fun take(card: Card) {
        cardPocket.add(card)
    }
}
