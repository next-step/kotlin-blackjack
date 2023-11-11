package blackjack.domain

class SoftAcePointStrategy : CardPointStrategy {
    override fun getPoint(rank: Rank): Int {
        return when(rank) {
            Rank.Ace -> 11
            else -> rank.value
        }
    }
}
