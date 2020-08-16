package blackjack.model

const val WIN_SCORE = 21

class Player(val name: String) {
    val cards: Cards = Cards(emptyList())

    fun draw(card: Card) {
        cards.add(card)
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
}
