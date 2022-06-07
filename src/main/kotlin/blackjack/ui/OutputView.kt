package blackjack.ui

import blackjack.domain.NormalPlayer

object OutputView {

    fun showDistribution(players: List<NormalPlayer>) {
        val names = getNames(players)
        println("$names 에게 2장의 카드를 나누었습니다.")
        players.forEach {
            showHand(it)
        }
    }

    private fun getNames(players: List<NormalPlayer>): String {
        return players.joinToString { it.name }
    }

    fun showHand(player: NormalPlayer) {
        println("${player.name}카드: ${player.hand}")
    }

    fun showResult(players: List<NormalPlayer>) {
        println()
        players.forEach {
            println("${it.name}카드: ${it.hand} - 결과: ${it.calculateHand()}")
        }
    }
}
