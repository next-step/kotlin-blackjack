package blackjack.business

fun interface CanDrawCardStrategy {
    fun canDrawCard(sum: Int): Boolean
}
