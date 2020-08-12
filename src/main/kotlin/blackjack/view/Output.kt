package blackjack.view

import blackjack.model.Player

object Output {
    fun players(players: List<Player>) {
        println("${players.joinToString(",") { "${it.name}" }}에게 2장의 나누었습니다.")
    }

    fun pickResult(player: Player) {
        println("${player.name}의 카드 : ${player.cards.joinToString { it.cardName() }}")
    }

    fun gameResult(players: List<Player>) {
        players.forEach {
            println("${it.name}의 카드: ${it.cards.joinToString { it.cardName() }} - 결과 : ${it.countingPoint()}")
        }
    }

    fun winner(players: List<Player>) {
        println("우승자 : ${players.joinToString { "${it.name}" }}")
    }
}
