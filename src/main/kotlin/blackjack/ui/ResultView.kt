package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.DealerResult
import blackjack.domain.GameResult
import blackjack.domain.Participant
import blackjack.domain.ParticipantResult
import blackjack.domain.PlayerResult

object ResultView {
    fun printFirstPhase(participants: List<Participant>) {
        printDrawTwoCardsEach(participants)
        participants.forEach { printParticipantsCards(it) }
        println()
    }

    private fun printDrawTwoCardsEach(participants: List<Participant>) {
        participants.joinToString { it.name }.also {
            println("${it}에게 2장의 카드를 나누었습니다.")
        }
    }

    fun printParticipantsCards(participant: Participant) {
        println("${participant.name} 카드: ${getCardsToStringInfo(participant)}")
    }

    private fun getCardsToStringInfo(player: Participant): String {
        val cards = player.cards
        return cards.joinToString(", ") { "${it.rank.score} ${it.suit}" }
    }

    fun printFinalResult(result: GameResult) {
        val participantResult = result.playerResults + result.dealerResult
        participantResult.forEach { printScoreResult(it) }
        printWinnerInfos(result)
    }

    private fun printScoreResult(result: ParticipantResult) {
        println("${result.name} 카드 : ${getCardsToStringInfo(result)}" + " - 결과 : ${result.score}")
    }

    private fun printWinnerInfos(result: GameResult) {
        println("## 최종 승패")
        printDealerEarningMoney(result.dealerResult)
        printPlayerEarningMoneys(result.playerResults)
    }

    private fun printDealerEarningMoney(dealerResult: DealerResult) {
        println("${dealerResult.name} : ${dealerResult.earningMoney}")
    }

    private fun printPlayerEarningMoneys(playerResults: List<PlayerResult>) {
        playerResults.forEach { printPlayerEarningMoney(it) }
    }

    private fun printPlayerEarningMoney(playerResult: PlayerResult) {
        println("${playerResult.name} : ${playerResult.earningMoney}")
    }

    private fun getCardsToStringInfo(participantResult: ParticipantResult): String {
        return participantResult.cards.joinToString(", ") { "${it.rank.score} ${it.suit}" }
    }

    fun printDealerDrawMessage(dealer: Dealer) {
        println("${dealer.name}는 16 이하라 한장의 카드를 더 받았습니다.")
    }
}
