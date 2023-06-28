package blackjack.domain

@JvmInline
value class Ranks(val values: Map<Player, PlayerRank>) {
    fun getDealerWonCounts(): Int {
        return values.count { it.value == PlayerRank.LOST }
    }

    fun getDealerLostCounts(): Int {
        return values.count { it.value == PlayerRank.WON }
    }
}
