package blackjack.model

data class Cards(private val cards: List<Card>) {

    val size = cards.size

    private val sum: Int by lazy {
        cards.asSequence()
            .sortedByDescending { it.denomination.defaultScore }
            .map { it.denomination }
            .fold(0) { acc, denomination ->
                if (denomination.isAce() && acc + ACE_BONUS_SCORE <= TWENTY_ONE) {
                    acc + ACE_BONUS_SCORE
                } else {
                    acc + denomination.defaultScore
                }
            }
    }

    init {
        require(cards == cards.distinct())
    }

    fun add(card: Card) = Cards(cards + card)

    operator fun plus(card: Card) = add(card)

    fun peek(): Card? = cards.firstOrNull()

    fun draw(): Cards = Cards(cards.drop(1))

    fun isEmpty(): Boolean = size == 0

    fun sum(): Int = sum

    fun toList(): List<Card> = cards

    operator fun contains(card: Card): Boolean = card in cards

    companion object {
        private const val TWENTY_ONE = 21
        private const val ACE_BONUS_SCORE = 11

        fun empty() = Cards(emptyList())
    }
}
