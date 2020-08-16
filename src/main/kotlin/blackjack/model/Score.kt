package blackjack.model

data class Score(
    val isWin: Boolean = false,
    val isDraw: Boolean = false,
    val isBlackJack: Boolean = false,
    val bettingMoney: Int = 0
)
