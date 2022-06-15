package game.blackjack.domain

class Cards(
    private val cards: MutableList<Card>
) {
    private val score: Int

    init {
        score = score()
    }

    constructor() : this(mutableListOf())

    fun get(): List<Card> = cards.toList()

    fun score(): Int = Card.score(cards)

    fun isBust(): Boolean = score() > BLACKJACK_SCORE

    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
