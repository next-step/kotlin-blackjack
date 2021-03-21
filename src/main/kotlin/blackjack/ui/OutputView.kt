package blackjack.ui

import blackjack.domain.GameParticipants

object OutputView {

    fun printPlayerInfo(players: List<GameParticipants>) {
        println("${players.map { it.name }}에게 2장을 나누었습니다.")
        for (player in players) {
            println("${player.name}: ${player.cards.getCardList()}")
        }
    }

    fun printPlayersCardList(players: List<GameParticipants>) {
        for (player in players) {
            println("[${player.name}] 카드: ${player.cards.getCardList()}")
        }
    }

    fun printPlayerCardList(player: GameParticipants) {
        println("[${player.name}] 카드: ${player.cards.getCardList()}")
    }

    fun printGameResult(players: List<GameParticipants>) {
        for (player in players) {
            println("Player[${player.name}] 카드 : ${player.cards.getCardList()} - 결과: ${player.calculateMyCards()}")
        }
    }
}
