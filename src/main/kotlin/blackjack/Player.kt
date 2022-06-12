package blackjack

class Player(
    private val name: String,
    private val hand: Cards = Cards(ArrayDeque(listOf())),
) {
    fun addCardToHand(card: Card) {
        this.hand.add(card)
    }

    fun handSize(): Int {
        return this.hand.size()
    }
}
