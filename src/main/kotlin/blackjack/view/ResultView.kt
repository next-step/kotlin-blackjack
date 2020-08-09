package blackjack.view

import blackjack.model.Player

object ResultView {

    fun showPlayer(player: Player) {
        println("${player.name}의 카드 : ${player.cards}")
    }

    fun showResult(players: List<Player>) {
        players.forEach {
            println("${it.name}의 카드 : ${it.cards}  - ${it.getScore()}")
        }
    }
}
