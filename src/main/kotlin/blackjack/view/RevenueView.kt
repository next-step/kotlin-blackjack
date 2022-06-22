package blackjack.view

import blackjack.domain.player.PlayerRevenues

class RevenueView(
    private val playerRevenues: PlayerRevenues
) {
    fun title() {
        println()
        println("## 최종 수익")
    }

    fun indicator() {
        println("딜러: ${playerRevenues.dealerRevenue}")
        playerRevenues.players.forEach {
            println("${it.player.name}: ${it.revenue}")
        }
    }
}
