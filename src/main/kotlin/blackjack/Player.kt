package blackjack

class Player(
    private val name: String,
    private val hand: Cards = Cards(),
) {
    fun addCardToHand(card: Card): Player {
        return Player(name = this.name, hand = this.hand.add(card))
    }

    fun handSize(): Int {
        return this.hand.size()
    }
}
