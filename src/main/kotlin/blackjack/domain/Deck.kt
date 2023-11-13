package blackjack.domain

class Deck(private val cards: MutableList<Card>, private val shuffleStrategy: ShuffleStrategy) : List<Card> by cards {

    private val initialCards = cards.toList()

    init {
        require(cards.size == INITIAL_DECK_SIZE) { "카드의 수가 ${INITIAL_DECK_SIZE}장이 아닙니다." }
    }

    fun draw(): Card {
        check(cards.isNotEmpty()) { "카드가 모두 소진되었습니다." }

        return cards.removeFirst()
    }

    fun shuffle() {
        val shuffledCards = shuffleStrategy.shuffle(cards)

        cards.clear()
        cards.addAll(shuffledCards)
    }

    fun reset() {
        cards.clear()
        cards.addAll(initialCards.toList())
    }

    companion object {
        private const val INITIAL_DECK_SIZE = 52
    }
}

class DeckDsl(private val shuffleStrategy: ShuffleStrategy = RandomShuffleStrategy) {
    private val cards: MutableList<Card> = mutableListOf()

    operator fun Card.unaryPlus() {
        cards.add(this)
    }

    fun build(): Deck = Deck(cards, shuffleStrategy)
}

fun deck(shuffleStrategy: ShuffleStrategy, init: DeckDsl.() -> Unit): Deck =
    DeckDsl(shuffleStrategy).apply(init).build()
