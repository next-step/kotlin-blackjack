package blackjack.domain

data class Dealer(
    override val name: String = "dealer",
    override val myCards: Cards = Cards()
) : Participant(name, myCards) {

    private val cardDeck: CardDeck = CardDeck()

    override fun toString(): String {
        return "${name}카드: $myCards"
    }

    fun draw(): Card = cardDeck.draw()
}
