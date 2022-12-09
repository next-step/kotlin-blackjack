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
        val result = mutableListOf<Card>()
        for (number in CardNumbers().cardNumberList) {
            for (suit in Suits().suitList) {
                result.add(Card(cardNumber = number, suit = suit))
            }
        }
        return result.shuffled()
    }
}
