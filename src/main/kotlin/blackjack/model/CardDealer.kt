package blackjack.model

class CardDealer(val cardDeck: CardDeck) {
    fun getCards(count: Int): List<Card> = (1..count).map {
        getCard()
    }

    fun getCard(): Card {
        val value = CardValue.getRandom()
        val suit = CardSuit.getRandom()
        return Card("${value.cardValue}${suit.cardSuit}", value)
    }
}
