package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

object ResultView {

    fun showPlayer(player: Player) {
        println("$player")
    }

    fun showPlayers(players: Players) {
        println("$players")
    }

    fun showResult(players: Players) {
        println("-- 게임 결과 -- ")
        println("$players")
    }
}
