package blackjack.domain

class HardAcePointStrategy : CardPointStrategy {
    override fun getPoint(rank: Rank): Int {
        return rank.value
    }
}
