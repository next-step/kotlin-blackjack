package blackjack.model

class CardDealer(private val cardDeck: CardDeck) {
    fun getCards(count: Int = FIRST_COUNT): List<Card> = (1..count).map {
        getCard()
    }

    fun getCard(): Card = cardDeck.drawCard()

    companion object {
        const val FIRST_COUNT = 2
    }
}
