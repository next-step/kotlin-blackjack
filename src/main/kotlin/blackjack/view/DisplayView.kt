package blackjack.view

import blackjack.domain.Players

object DisplayView {

    fun dealOutCards(players: Players) {
        val playersName = players.players.joinToString()
        println("${playersName}에게 2장의 카드 나누었습니다.")
    }

}
