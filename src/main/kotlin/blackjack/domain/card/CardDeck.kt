package blackjack.domain.card

class CardDeck(val cards: MutableList<Card>, val initDrawSize: Int = 2) {

    fun draw(): Card {
        require(cards.isNotEmpty()) { "카드 덱이 비어있습니다." }
        return cards.removeLast()
    }

    fun initDraw(): Cards = Cards(List(initDrawSize) { draw() })

    companion object {
        private val CARDS: List<Card> = CardSymbol.values().flatMap { cardSymbol ->
            CardNumber.values().map { cardNumber ->
                Card(cardNumber, cardSymbol)
            }
        }

        fun create(): CardDeck {
            return CardDeck(CARDS.shuffled().toMutableList())
        }
    }
}
