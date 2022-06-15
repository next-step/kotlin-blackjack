package blackjack.domain

class Player(val name: String, private var cards: Cards) {
    private val score = cards.score()

    fun couldGetMoreCard(): Boolean {
        return score.origin < BLACKJACK || score.alternative < BLACKJACK
    }

    fun result(): Int {
        return when {
            score.origin > BLACKJACK -> score.alternative
            score.alternative > BLACKJACK -> score.origin
            score.origin > score.alternative -> score.origin
            else -> score.alternative
        }
    }

    infix fun take(newCard: Cards) {
        cards += newCard
    }

    fun numberOfCards(): Int {
        return cards.size
    }

    override fun toString(): String {
        return "$name cards : $cards"
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
