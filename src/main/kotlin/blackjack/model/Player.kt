package blackjack.model

const val WIN_SCORE = 21

open class Player(val name: String, private val cards: Cards = Cards(emptyList())) {

    fun draw(vararg cards: Card) {
        repeat(cards.size) {
            this.cards.add(cards[it])
        }
    }

    fun score(): Int {
        return cards.score()
    }

    fun available(): Boolean {
        return score() < WIN_SCORE
    }

    fun winner(): Boolean {
        return score() == WIN_SCORE
    }

    fun cardToString(): String {
        return cards.toString()
    }
}
