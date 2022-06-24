package blackjack.card

class Deck private constructor(vararg cards: Card) {
    init {
        require(cards.size == DECK_INIT_CARD_SIZE) {
            "덱은 ${DECK_INIT_CARD_SIZE}장의 카드로 구성 돼 있습니다."
        }
    }

    private var _cards: MutableList<Card> = cards.toMutableList()
    val size = _cards.size

    fun draw(): Card {
        return _cards.removeFirst()
    }

    companion object {
        private const val DECK_INIT_CARD_SIZE = 52

        fun init(): Deck {
            val cards = Suit.values().flatMap { suit ->
                CardSymbol.values()
                    .map { symbol -> Card(suit, symbol) }
            }.toTypedArray()
                .also { it.shuffle() }

            return Deck(*cards)
        }
    }
}
