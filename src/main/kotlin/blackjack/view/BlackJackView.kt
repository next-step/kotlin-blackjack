package blackjack.view

import blackjack.domain.player.Players

object BlackJackView {

    fun printPlayersInitView(players: Players) {
        println("${players.players.joinToString(", ") { it.name }}에게 2장을 나누었습니다.")
    }

    fun printPlayersCardsView(players: Players) {
        players.players.forEach {
            PlayerView.printPlayerCardsView(it)
        }
        println()
    }

    fun printPlayersResultView(players: Players) {
        players.players.forEach {
            println("${it.name}카드: ${it.getHands().joinToString(", ")} - 결과: ${it.getHandsValue()}")
        }
    }
}
