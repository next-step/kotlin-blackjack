package blackjack.model

const val WIN_SCORE = 21

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards: Cards
        get() = Cards(_cards.deepCopy())
    var isAvailable: Boolean = true
        get() = score() < WIN_SCORE
    var isWinner: Boolean = false
        get() = score() == WIN_SCORE

    fun draw(card: Card) {
        _cards.add(card)
    }

    fun score(): Int {
        return cards.score()
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }
}
