package blackjack.view

import blackjack.model.Player

object ResultView {

    fun showPlayer(player: Player) {
        println("$player")
    }

    fun showPlayers(players: String) {
        println(players)
    }

    fun showResult(players: String) {
        println("-- 게임 결과 -- ")
        println(players)
    }
}
