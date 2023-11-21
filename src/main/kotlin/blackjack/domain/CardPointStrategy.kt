package blackjack.domain

fun interface CardPointStrategy {
    fun getPoint(rank: Rank): Int
}
