package view

import domain.player.Player
import domain.player.Players

object OutputView {
    fun firstDrawTo(players: Players) {
        println("${players}에게 2장의 카드를 나누었습니다.")
    }

    fun printHandsOf(player: Player) {
        println("${player.name} 카드 : ${player.handsStatus()}")
    }

    fun gameResult(players: Players) {
        repeat(players.size()){
            println("${players[it].name} 카드 : ${players[it].handsStatus()} - 결과 : ${players[it].score()}")
        }
    }
}
