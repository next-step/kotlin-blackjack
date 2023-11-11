package blackjack.domain

class Deck(private val cards: MutableList<Card>, private val shuffleStrategy: ShuffleStrategy) : List<Card> by cards {

    init {
        require(cards.size == INITIAL_DECK_SIZE) { "카드의 수가 52장이 아닙니다." }
    }

    fun draw(): Card = cards.removeFirst()

    fun shuffle() {
        val shuffledCards = shuffleStrategy.shuffle(cards)

        cards.clear()
        cards.addAll(shuffledCards)
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

fun deck(shuffleStrategy: ShuffleStrategy, init: DeckDsl.() -> Unit): Deck = DeckDsl().apply(init).build()
