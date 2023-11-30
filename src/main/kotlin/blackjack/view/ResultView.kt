package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun firstDealCard(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")

    }

    fun showPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.cards}")
    }


    fun showPlayerResult(players: List<Player>) {
        println()
        for (player in players) {
            println("${player.name}카드: ${player.cards.cards} - 결과: ${player.getScore()}")
        }
    }

}
