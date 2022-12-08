package domain
class CardDeck {

    val cardDeck = Card.values()

    fun drawCard() : Card {
        cardDeck.shuffle()
        return cardDeck[0]
    }

}
