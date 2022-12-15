package blackjack.model

class Player(
    val name: String,
) {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> = _cards

    fun addCard(card: Card) {
        check(isPickable())
        _cards.add(card)
    }

    fun isPickable(): Boolean {
        return getScore() < BLACKJACK_SCORE
    }

    private fun getScore(): Int {
        return _cards.sumOf { it.getScore() }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val DELIMITER = ","

        fun playersOf(input: String): List<Player> {
            return input.split(DELIMITER).map(::Player)
        }
    }
}
