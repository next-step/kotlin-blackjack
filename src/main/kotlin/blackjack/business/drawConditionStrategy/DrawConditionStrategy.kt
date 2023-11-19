package blackjack.business.drawConditionStrategy

fun interface DrawConditionStrategy {
    fun shouldDraw(playerName: String, onAction: (String) -> String): Boolean
}
