package blackjack.model.trump

interface Deck : MutableList<Card> {
    fun draw(): Card
}
