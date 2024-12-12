package blackjack.core.player

import blackjack.core.amount.BettingAmount

class Players(private val players: List<Player>) : List<Player> by players {
    constructor(names: List<Name>, bettingAmounts: List<BettingAmount>) : this(initPlayers(names, bettingAmounts))

    fun play(action: (Player) -> Unit) {
        forEach(action)
    }

    companion object {
        fun initPlayers(
            names: List<Name>,
            bettingAmounts: List<BettingAmount>,
        ): List<Player> {
            require(names.size == bettingAmounts.size) { ERROR_ENTRY_NOT_MATCHED }

            return names.zip(bettingAmounts).map { Player(it.first, it.second) }
        }

        private const val ERROR_ENTRY_NOT_MATCHED = "플레이어 수와 베팅금액이 일치하지 않습니다."
    }
}
