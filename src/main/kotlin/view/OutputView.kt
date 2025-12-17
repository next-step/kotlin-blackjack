package view

import domain.GameResult
import domain.Participant
import domain.Player
import domain.Players

object OutputView {
    fun printPlayerNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.")
    }

    fun printFirstCard(players: Players) {
        println("딜러와 ${players.players.joinToString(", ", transform = Player::name)}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardStatusOnFirstRound(participant: Participant) {
        println("${participant.name}카드: ${participant.getPublicCardsOnFirstRound()}")
    }

    fun printCardStatus(participant: Participant) {
        println("${participant.name}카드: ${participant.cards}")
    }

    fun printDoYouWantCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printDealerMustGetCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printRoundResult(participant: Participant) {
        println("${participant.name}카드: ${participant.cards} - 결과: ${participant.calculateScore()}")
    }

    fun printFinalResult(gameResult: GameResult) {
        println("## 최종 승패")
        println("딜러: ${gameResult.losers.size} 승 ${gameResult.winners.size} 패 ${gameResult.draws.size} 무")
        gameResult.winners.forEach { println("${it.name}: 승") }
        gameResult.losers.forEach { println("${it.name}: 패") }
        gameResult.draws.forEach { println("${it.name}: 무") }
    }
}
