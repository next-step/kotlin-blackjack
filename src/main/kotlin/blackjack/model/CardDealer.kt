package blackjack.model

class CardDealer(private val cardDeck: CardDeck) {
    fun getCards(count: Int): List<Card> = (1..count).map {
        getCard()
    }

    fun getCard(): Card = cardDeck.drawCard()

    companion object {
        const val FIRST_CARD_COUNT = 2
    }
}
