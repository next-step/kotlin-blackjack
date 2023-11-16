package blackjack.domain.card

class Deck(val cards: Cards) {
    init {
        require(cards.size == initialCardCount) { "Invalid initial Card count ${cards.size}" }
        require(cards.cardList.count { it.suit == Suit.Spade } == Character.values().size) { "Invalid suit count ${Suit.Spade}" }
        require(cards.cardList.count { it.suit == Suit.Diamond } == Character.values().size) { "Invalid suit count ${Suit.Diamond}" }
        require(cards.cardList.count { it.suit == Suit.Heart } == Character.values().size) { "Invalid suit count ${Suit.Heart}" }
        require(cards.cardList.count { it.suit == Suit.Clover } == Character.values().size) { "Invalid suit count ${Suit.Clover}" }
        require(cards.cardList.toSet().size == initialCardCount) { "Duplicate cards ${cards.cardList}" }
    }

    fun draw(): Card = cards.drawTop()

    fun shuffle() {
        cards.shuffle()
    }

    fun cardCount(): Int = cards.size

    companion object {
        val initialCardCount = Suit.values().size * Character.values().size

        fun fullDeck(): Deck {
            return Deck(Cards.fullCards())
        }
    }
}
