package blackjack.domain

open class Player(val name: String, var cards: Cards = Cards()) {
    val score: Int
        get() = cards.getScore()
    fun hit(card: Card) {
        cards.add(card)
    }

    fun isBust() = cards.getScore() > TWENTY_ONE

    fun isBlackJack() = cards.isBlackJack()

    companion object {
        private const val TWENTY_ONE = 21
    }
}
