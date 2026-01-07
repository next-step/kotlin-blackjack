package view

import model.BlackJackConstants
import model.GameResult
import model.Participant
import model.Player
import model.Players

object OutputView {
    fun printFirstCard(players: Players) {
        println("딜러와 ${players.joinToString(", ", transform = Player::name)}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardStatusOnFirstRound(participant: Participant) {
        println("${participant.name}카드: ${participant.getPublicCardsOnFirstRound()}")
    }

    fun printCardStatus(participant: Participant) {
        println("${participant.name}카드: ${participant.cards}")
    }

    fun printDealerMustGetCard() {
        println("딜러는 ${BlackJackConstants.DEALER_DRAW_THRESHOLD}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printRoundResult(participant: Participant) {
        println("${participant.name}카드: ${participant.cards} - 결과: ${participant.calculateScore()}")
    }

    fun printFinalResult(gameResult: GameResult) {
        println("## 최종 수익")
        println("딜러: ${gameResult.dealerWin}")
        gameResult.playerWins.forEach { (name, win) ->
            println("$name: $win")
        }
        println()
    }
}
