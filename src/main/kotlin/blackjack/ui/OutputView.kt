package blackjack.ui

import blackjack.domain.Player

object OutputView {

    fun showDistribution(dealer: Player, players: List<Player>) {
        val names = getNames(players)
        println("${dealer.name}와 ${names}에게 2장의 카드를 나누었습니다.")
        showHand(dealer)
        players.forEach {
            showHand(it)
        }
    }

    private fun getNames(players: List<Player>): String {
        return players.joinToString { it.name }
    }

    fun showHand(player: Player) {
        println("${player.name}카드: ${player.hand}")
    }

    fun showResult(players: List<Player>) {
        println()
        players.forEach {
            println("${it.name}카드: ${it.hand} - 결과: ${it.calculateHand()}")
        }
    }
}
