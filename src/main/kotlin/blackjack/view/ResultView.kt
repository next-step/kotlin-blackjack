package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.GameTable.Companion.INIT_CARD_DRAW_COUNT
import blackjack.domain.Participant

object ResultView {
    fun printInitCardReceive(participants: List<Participant>) {
        val players = Participant.separate(participants).first
        println("딜러와 ${players.joinToString(", ") { it.name }}에게 ${INIT_CARD_DRAW_COUNT}장의 카드를 나누었습니다.")
    }

    fun printParticipantsCard(
        participants: List<Participant>,
        printScore: Boolean,
    ) {
        val (players, dealer) = Participant.separate(participants)
        printParticipantCard(participant = dealer, printScore = printScore)
        players.forEach { printParticipantCard(participant = it, printScore = printScore) }
    }

    fun printParticipantCard(
        participant: Participant,
        printScore: Boolean,
    ) {
        val cards = participant.cards.values.joinToString(", ") { "${it.rank.value}${it.suit.description}" }
        val scoreText = "- 결과: ${participant.cards.score}"
        println("${participant.name} 카드: $cards ${if (printScore) scoreText else ""}")
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(gameResult: GameResult) {
        val winMessage =
            if (gameResult.dealerGameResult.winCount > 0) "${gameResult.dealerGameResult.winCount}승" else ""
        val lossMessage =
            if (gameResult.dealerGameResult.lossCount > 0) "${gameResult.dealerGameResult.lossCount}패" else ""
        val drawMessage =
            if (gameResult.dealerGameResult.drawCount > 0) "${gameResult.dealerGameResult.drawCount}무" else ""
        println("## 최종 승패")
        println("딜러: $winMessage $lossMessage $drawMessage")
        gameResult.playerGameResults.forEach {
            println("${it.player.name} ${it.result.description}")
        }
    }

    fun linebreak() {
        println()
    }
}
