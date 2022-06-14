package blackjack.domain.card

class Cards(cardList: List<Card> = emptyList()) {

    private val cardList = cardList.toMutableList()

    val total: CardTotal = CardTotal(this.cardList)

    val list: List<Card>
        get() = cardList.toList()

    fun shuffle() {
        cardList.shuffle()
    }

    fun getNames(): List<String> = cardList.map { it.getName() }

    operator fun get(index: Int): Card = cardList[index]

    operator fun plusAssign(card: Card) {
        cardList.add(card)
    }

    companion object {
        private fun getAllCardsOfSuit(suit: CardSuit): List<Card> = listOf(
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

        fun getAll(): Cards = Cards(
            CardSuit
                .values()
                .flatMap { getAllCardsOfSuit(it) }
        )
    }
}
