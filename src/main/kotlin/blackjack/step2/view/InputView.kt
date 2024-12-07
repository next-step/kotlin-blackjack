package blackjack.step2.view

import blackjack.step2.domain.GameManager
import blackjack.step2.domain.PlayerCard
import blackjack.step2.domain.ScoreCalculator

object InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }.filter { it.isNotBlank() }
    }

    fun inputMoreCard(
        playerCards: List<PlayerCard>,
        gameManager: GameManager,
    ): List<PlayerCard> {
        return playerCards.map { playerCard ->
            handleCardDecision(playerCard, gameManager)
        }
    }

    private fun handleCardDecision(
        playerCard: PlayerCard,
        gameManager: GameManager,
    ): PlayerCard {
        var currentCard = playerCard
        while (currentCard.calculateScore() < ScoreCalculator.BLACKJACK_SCORE) {
            println("${currentCard.playerName}는 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val input = readln().trim().lowercase()

            when (input) {
                "y" -> {
                    println("${currentCard.playerName}는 한 장의 카드를 더 받습니다.")
                    currentCard = gameManager.pickCardIfValid(currentCard)
                }

                "n" -> {
                    println("${currentCard.playerName}는 카드를 더 받지 않습니다.")
                    break
                }

                else -> {
                    println("잘못된 입력입니다. 다시 입력해주세요.")
                }
            }
        }
        return currentCard
    }
}
