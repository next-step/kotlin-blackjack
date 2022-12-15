package blackjack.model

class Player(
    val name: String,
    private val cards: MutableList<Card> = mutableListOf()
) : List<Card> by cards {
    fun addCard(card: Card) {
        check(isPickable())
        cards.add(card)
    }

    fun isPickable(): Boolean {
        return getScore() < BLACKJACK_SCORE
    }

    private fun getScore(): Int {
        return cards.sumOf { it.getScore() }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val DELIMITER = ","

        fun playersOf(input: String): List<Player> {
            return input.split(DELIMITER).map(::Player)
        }
    }
}
