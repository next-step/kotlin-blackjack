package blackjack.domain

class CardDeck {

    private var cardList: List<Card> = initList()

    fun draw(): Card {
        val card = cardList[0]
        cardList = cardList.drop(1)
        return card
    }

    fun getCardList(): List<Card> = cardList.toList()

    private fun initList(): List<Card> {
        return CardNumber.values()
            .flatMap { cardNumber ->
                Suit.values().map { suit ->
                    Card(cardNumber = cardNumber, suit = suit)
                }
            }
            .shuffled()
    }
}
