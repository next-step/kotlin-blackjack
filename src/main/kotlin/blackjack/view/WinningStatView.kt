package blackjack.view

import blackjack.domain.winning.WinningStat

class WinningStatView(
    private val winningStat: WinningStat
) {
    private val winningResult = winningStat.playerGameResult()

    fun title() {
        println()
        println("## 최종 승패")
    }

    fun indicator() {
        when (winningStat.dealerWinning()) {
            true -> winningResult.forEach {
                if (it.player.name == "딜러") println("${it.player.name} : 패")
                else println("${it.player.name} : 승")
            }
            false -> winningResult.forEach {
                println("${it.player.name} : ${it.winCount}승 ${it.looseCount}패 ${it.tieCount}무")
            }
        }
    }
}
