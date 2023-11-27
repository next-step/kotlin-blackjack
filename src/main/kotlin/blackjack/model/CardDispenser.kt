package blackjack.model

class CardDispenser(private val cardDeck: CardDeck) {
    fun getCards(count: Int): List<Card> = (1..count).map {
        getCard()
    }

    fun getCard(): Card = cardDeck.drawCard()
}
