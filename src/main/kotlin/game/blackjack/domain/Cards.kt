package game.blackjack.domain

class Cards(
    private val cards: MutableList<Card>
) {
    private var score: Score

    init {
        score = score()
    }

    constructor() : this(mutableListOf())

    fun get(): List<Card> = cards.toList()

    fun score(): Score = Card.score(cards)

    fun isBust(): Boolean = score.isBust()

    fun add(card: Card) {
        cards.add(card)
        score = Card.score(cards)
    }

    fun size(): Int = cards.size
}
