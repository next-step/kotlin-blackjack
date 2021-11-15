package blackjack

data class Cards(private val cards: List<Card>) {

    val size = cards.size

    init {
        require(cards == cards.distinct())
    }

    fun add(card: Card) = Cards(cards + card)

    operator fun plus(card: Card) = add(card)

    fun peek(): Card? = cards.firstOrNull()

    fun draw(): Cards = Cards(cards.drop(1))

    fun isEmpty(): Boolean = size == 0

    fun sum(): Int = ScoreHelper.sum(cards)

    operator fun contains(card: Card): Boolean = card in cards

    companion object {
        fun empty() = Cards(emptyList())
    }
}
