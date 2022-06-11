package blackjack.domain.card

class Cards(cardList: List<Card> = emptyList()) {

    private val cardList = cardList.toMutableList()

    val total: CardTotal = CardTotal(this.cardList)

    fun shuffle() {
        cardList.shuffle()
    }

    fun getCardAt(index: Int): Card = cardList[index]


    operator fun plusAssign(card: Card) {
        cardList.add(card)
    }
}
