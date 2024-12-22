package blackjack.view

import blackjack.domain.DealerOutcomes
import blackjack.domain.EarningMoneyResult
import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.PlayerOutcomes
import blackjack.domain.Result

object OutputView {
    fun showGameStart(participants: Participants) {
        val playerNames = participants.members.map { gerParticipantName(it) }

        println(String.format(DEAL_RESULT_MESSAGE, playerNames))
        participants.members.forEach {
            println("${gerParticipantName(it)} 카드: ${it.ownedCards}")
        }
        println()
    }

    fun printPlayerCards(participant: Participant) {
        println(String.format(CURRENT_CARD_STATUS, gerParticipantName(participant), participant.ownedCards))
    }

    fun showGameResult(participants: Participants) {
        participants.members.forEach {
            println(String.format(GAME_RESULT_MESSAGE, gerParticipantName(it), it.ownedCards, it.sumOfCard()))
        }
    }

    fun dealerHitResult() {
        println(DEALER_HIT_MESSAGE)
    }

    fun showDealerWinningCount(results: DealerOutcomes) {
        val winCount = results.numberOfWins()
        val loseCount = results.numberOfLose()
        println("딜러: $winCount ${parseResult(Result.WIN)} $loseCount ${parseResult(Result.LOSE)}")
    }

    fun showWinnerPlayers(playerOutcomes: List<PlayerOutcomes>) {
        playerOutcomes.forEach {
            println("${gerParticipantName(it.participant)}: ${parseResult(it.results)}")
        }
    }

    private fun parseResult(result: Result): String =
        when (result) {
            Result.WIN -> "승"
            Result.LOSE -> "패"
        }

    fun showEarningMoneyResult(earningMoneyResults: List<EarningMoneyResult>) {
        println(EARNING_MONEY_RESULT_MESSAGE)
        earningMoneyResults.forEach {
            println("${gerParticipantName(it.participant)}: ${it.amount}")
        }
    }

    private fun gerParticipantName(participant: Participant): String {
        if (participant is Participant.Player) return participant.name
        return "딜러"
    }

    private const val DEAL_RESULT_MESSAGE = "%s 에게 2장의 카드를 카드를 나누었습니다."
    private const val CURRENT_CARD_STATUS = "%s카드: %s"
    private const val GAME_RESULT_MESSAGE = "%s카드: %s - 결과 %d"
    private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val EARNING_MONEY_RESULT_MESSAGE = "## 최종수익"
}
