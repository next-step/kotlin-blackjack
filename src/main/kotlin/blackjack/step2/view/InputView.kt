package blackjack.step2.view

import blackjack.step2.domain.Dealer
import blackjack.step2.domain.GameManager
import blackjack.step2.domain.PlayerCard
import blackjack.step2.domain.PlayerCards
import blackjack.step2.domain.Players
import blackjack.step2.domain.RandomCardPicker

object InputView {
    fun inputPlayerNames(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return Players.of(readln().split(","))
    }

    fun inputMoreCard2(playerCards: PlayerCards): PlayerCards {
        playerCards.cards.forEach {
            this.printAndHandleCardDecision(it)
        }
        return playerCards
    }

    fun inputMoreCard(
        playerCards: PlayerCards,
        gameManager: GameManager,
    ): PlayerCards {
        playerCards.cards.forEach { playerCard ->
            handleCardDecision(playerCard, gameManager)
        }
        return playerCards
    }

    private fun handleCardDecision(
        playerCard: PlayerCard,
        gameManager: GameManager,
    ) {
        while (playerCard.calculateScore() < 21) { // 점수 21 초과 여부 확인
            println("${playerCard.playerName}는 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val input = readln().trim().lowercase()

            when (input) {
                "y" -> {
                    println("${playerCard.playerName}는 한 장의 카드를 더 받습니다.")
                    gameManager.dealCardIfValid(playerCard)
                }
                "n" -> {
                    println("${playerCard.playerName}는 카드를 더 받지 않습니다.")
                    break
                }
                else -> {
                    println("잘못된 입력입니다. 다시 입력해주세요.")
                }
            }
        }

        if (playerCard.calculateScore() >= 21) {
            println("${playerCard.playerName}의 점수가 21을 초과하여 카드를 받을 수 없습니다.")
        }
    }

    private fun printAndHandleCardDecision(playerCard: PlayerCard) {
        while (true) {
            println("${playerCard.playerName}는 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val input = readln().trim().lowercase()

            when (input) {
                "y" -> {
                    println("${playerCard.playerName}는 한장의 카드를 더 받겠습니다.")
                    Dealer(RandomCardPicker()).dealCard(playerCard)
                }
                "n" -> {
                    println("${playerCard.playerName}는 카드를 더 받지 않겠습니다.")
                    break // n 입력 시 루프 종료
                }
                else -> {
                    println("잘못된 입력입니다. 다시 입력해주세요.")
                    // 루프 반복
                }
            }
        }
    }
}
