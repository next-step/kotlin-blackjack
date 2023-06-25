package blackjack.domain.card

class CardDeck(
    val cards: MutableList<Card> = CARDS.shuffled().toMutableList(),
    val initDrawSize: Int = INIT_DRAW_SIZE
) {

    fun draw(count: Int = 1): Cards {
        require(count >= MINIMUM_DRAW_SIZE) { "카드 드로우 갯수는 $MINIMUM_DRAW_SIZE 이상입니다." }
        require(cards.size > count) { "드로우 갯수에 비해 카드 수가 부족합니다." }
        return Cards(List(count) { cards.removeLast() })
    }

    companion object {
        private val CARDS: List<Card> = CardSymbol.values().flatMap { cardSymbol ->
            CardNumber.values().map { cardNumber ->
                Card(cardNumber, cardSymbol)
            }
        }

        const val MINIMUM_DRAW_SIZE = 1
        const val INIT_DRAW_SIZE = 2
    }
}
