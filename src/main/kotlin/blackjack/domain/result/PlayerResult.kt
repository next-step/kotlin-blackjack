package blackjack.domain.result

import blackjack.domain.Player

data class PlayerResult(
    val player: Player,
    val earningRate: Double
) {
    fun getEarnMoney(): Int {
        return (player.bet * earningRate).toInt()
    }
}
