package blackjack.domain.card

class Cards(cardList: List<Card> = emptyList()) {

    private val cardList = cardList.toMutableList()

    val total: CardTotal = CardTotal(this.cardList)

    fun shuffle() {
        cardList.shuffle()
    }

    fun getCardAt(index: Int): Card = cardList[index]

    fun getNames(): List<String> = cardList.map { it.getName() }

    operator fun plusAssign(card: Card) {
        cardList.add(card)
    }
    private operator fun plus(other: Cards): Cards {
        return Cards(cardList + other.cardList)
    }

    companion object {
        private fun getAllCardsOfSuit(suit: CardSuit): Cards = Cards(
            listOf(
                Card.Two(suit),
                Card.Three(suit),
                Card.Four(suit),
                Card.Five(suit),
                Card.Six(suit),
                Card.Seven(suit),
                Card.Eight(suit),
                Card.Nine(suit),
                Card.Ten(suit),
                Card.Jack(suit),
                Card.Queen(suit),
                Card.King(suit),
                Card.Ace(suit)
            )
        )

        fun getAll(): Cards = getAllCardsOfSuit(CardSuit.CLOVER) +
            getAllCardsOfSuit(CardSuit.HEART) +
            getAllCardsOfSuit(CardSuit.DIAMOND) +
            getAllCardsOfSuit(CardSuit.SPADE)
    }
}
