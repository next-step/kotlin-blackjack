package blackjack.domain

data class DealerResult(val isBust: Boolean, var win: Int, var lose: Int, var draw: Int)
