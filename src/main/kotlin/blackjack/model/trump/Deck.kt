package blackjack.model.trump

interface Deck : MutableList<Card> {
    fun peekCard(cardNumber: CardNumber, suit: Suit): Card
    fun draw(): Card
}
