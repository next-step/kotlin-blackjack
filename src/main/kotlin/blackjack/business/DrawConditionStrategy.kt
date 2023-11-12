package blackjack.business

fun interface DrawConditionStrategy {
    fun shouldDraw(playerName: String): Boolean
}
