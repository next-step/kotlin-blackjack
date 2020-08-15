package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

object Output {
    fun players(players: Players) {
        println("${players}에게 2장의 나누었습니다.")
    }

    fun pickResult(player: Player) {
        println("${player.name}의 카드 : ${player.cards}")
    }

    fun gameResult(players: Players) {
        players
    }

    fun winner(players: Players) {
        println("우승자 : $players")
    }
}
