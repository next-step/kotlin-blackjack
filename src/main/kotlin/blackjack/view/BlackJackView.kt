package blackjack.view

import blackjack.domain.player.Players

object BlackJackView {
    fun printPlayerInputView() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

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
            println("${it.name}카드: ${it.cards.joinToString(", ")} - 결과: ${it.getCardsValue()}")
        }
    }
}
