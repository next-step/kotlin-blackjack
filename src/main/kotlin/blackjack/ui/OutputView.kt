package blackjack.ui

import blackjack.domain.GameParticipants
import blackjack.domain.Participants

object OutputView {

    fun printPlayerInfo(players: Participants) {
        println("${players.value.map { it.name }}에게 2장을 나누었습니다.")
        for (player in players.value) {
            println("${player.name}: ${player.cards.getCardList()}")
        }
    }

    fun printPlayersCardList(players: Participants) {
        for (player in players.value) {
            println("[${player.name}] 카드: ${player.cards.getCardList()}")
        }
    }

    fun printPlayerCardList(player: GameParticipants) {
        println("[${player.name}] 카드: ${player.cards.getCardList()}")
    }

    fun printGameResult(players: Participants) {
        println("\n--------- 게임 결과 ------------")
        for (player in players.value) {
            println("${player.name} 카드 : ${player.cards.getCardList()} - 결과: ${player.calculateMyCards()}")
        }
    }
}
