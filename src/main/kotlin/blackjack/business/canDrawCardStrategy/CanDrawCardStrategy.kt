package blackjack.business.canDrawCardStrategy

fun interface CanDrawCardStrategy {
    fun canDrawCard(sum: Int): Boolean
}
