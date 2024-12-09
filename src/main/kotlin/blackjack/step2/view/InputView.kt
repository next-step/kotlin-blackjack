package blackjack.step2.view

import blackjack.step2.domain.GameManager
import blackjack.step2.domain.Participant
import blackjack.step2.domain.ScoreCalculator

object InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }.filter { it.isNotBlank() }
    }

    fun inputMoreCard(
        participants: List<Participant>,
        gameManager: GameManager,
    ): List<Participant> {
        return participants.map { participant ->
            handleCardDecision(participant, gameManager)
        }
    }

    private fun handleCardDecision(
        participant: Participant,
        gameManager: GameManager,
    ): Participant {
        if (participant.calculateScore() >= ScoreCalculator.BLACKJACK_SCORE) {
            return participant
        }

        println("${participant.name}는 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln().trim().lowercase()

        return when (input) {
            "y" -> {
                println("${participant.name}는 한 장의 카드를 더 받습니다.")
                val updatedParticipant = gameManager.pickPlayerCardIfValid(participant)
                handleCardDecision(updatedParticipant, gameManager)
            }

            "n" -> {
                println("${participant.name}는 카드를 더 받지 않습니다.")
                participant
            }

            else -> {
                println("잘못된 입력입니다. 다시 입력해주세요.")
                handleCardDecision(participant, gameManager)
            }
        }
    }
}
