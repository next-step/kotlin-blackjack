package blackjack.domain.game

enum class Rank(private val value: Double) {
    BLACKJACK(1.5),
    WON(1.0),
    LOST(-1.0),
    DRAW(1.0),
    ;

    operator fun times(amount: Int): Int {
        return (amount * value).toInt()
    }
}
