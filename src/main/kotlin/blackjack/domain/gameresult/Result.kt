package blackjack.domain.gameresult

import blackjack.domain.Money

enum class Result(val ratio: Double) {
    BLACKJACK(1.5),
    BLACKJACK_DRAW(1.0),
    WIN(1.0),
    DRAW(-1.0),
    LOSE(-1.0);

    fun profit(money: Money): Int = (money.value * ratio).toInt()
}
