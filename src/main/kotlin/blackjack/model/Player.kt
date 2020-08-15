package blackjack.model

const val WIN_SCORE = 21

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.deepCopy()
    var isAvailable: Boolean = true
        get() = countingPoint() < WIN_SCORE
    var isWinner: Boolean = false
        get() = countingPoint() == WIN_SCORE

    fun pickCard(card: Card) {
        _cards.add(card)
    }

    fun countingPoint(): Int {
        val value1 = _cards.sumBy { it.denomination.value1 }
        val value2 = _cards.sumBy { it.denomination.value2 }
        return when {
            value1 == WIN_SCORE -> value1
            value2 == WIN_SCORE -> value2
            else -> value1
        }
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }
}
