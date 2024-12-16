package blackjack.view

import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.PlayerOutcomes

object OutputView {
    fun showGameStart(participants: Participants) {
        val playerNames = participants.allPlayers().map { it.name }

        println(String.format(DEAL_RESULT_MESSAGE, playerNames))
        participants.allPlayers().forEach {
            println("${it.name}카드: ${it.ownedCards}")
        }
        println()
    }

    fun printPlayerCards(participant: Participant) {
        println(String.format(CURRENT_CARD_STATUS, participant.name, participant.ownedCards))
    }

    fun showGameResult(participants: Participants) {
        participants.allPlayers().forEach {
            println(String.format(GAME_RESULT_MESSAGE, it.name, it.ownedCards, it.sumOfCard()))
        }
    }

    fun dealerHitResult() {
        println(DEALER_HIT_MESSAGE)
    }

    fun showWinnerPlayers(
        dealerWinningCount: Int,
        dealerLoseCount: Int,
        playerOutcomes: List<PlayerOutcomes>,
    ) {
        println("딜러: $dealerWinningCount 승 $dealerLoseCount 패")
        playerOutcomes.forEach {
            println("${it.participant.name}: ${it.results.message}")
        }
    }

    private const val DEAL_RESULT_MESSAGE = "%s 에게 2장의 카드를 카드를 나누었습니다."
    private const val CURRENT_CARD_STATUS = "%s카드: %s"
    private const val GAME_RESULT_MESSAGE = "%s카드: %s - 결과 %d"
    private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
}
