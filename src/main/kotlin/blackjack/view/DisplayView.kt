package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object DisplayView {

    fun dealOutCards(players: Players) {
        val playersName = players.players.joinToString()
        println("${playersName}에게 2장의 카드 나누었습니다.")
    }

    fun cardsOfPlayers(players: Players) {
        players.players.forEach {
            cardsOfPlayer(it)
        }
    }

    fun cardsOfPlayer(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun dealOutAdditionalCard(player: Player) {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun result(players: Players) {
        players.players.forEach {
            println("${it.name}카드: ${it.cards} - 결과: ${it.getScore()}")
        }
    }

}
