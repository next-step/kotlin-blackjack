package blackjack.view

import blackjack.domain.Card
import blackjack.view.dto.AddCardResult
import blackjack.view.dto.PlayerGameResult
import blackjack.view.dto.InitResult

const val ZERO_COUNT = 0

object OutputView {
    fun printFirstDeal(players: List<String>) {
        println("딜러와 ${players.joinToString(", ")} 에게 2장의 카드를 나누었습니다.")
    }

    fun printCardsInit(initResults: List<InitResult>) {
        initResults.forEach { printCardsByPlayer(it.name, it.cards) }
    }

    fun printCardsAdded(addCardResult: AddCardResult) {
        printCardsByPlayer(addCardResult.name, addCardResult.cards)
    }

    private fun printCardsByPlayer(name: String, cards: Set<Card>) {
        print("${name}카드: ")
        println(cards.joinToString(", ") { it.korean })
    }

    fun printHitDealerResult(count: Int) {
        if (count > ZERO_COUNT) {
            println("딜러는 16이하라 ${count}장의 카드를 더 받았습니다.")
        }
    }

    fun printResult(playerDtos: List<PlayerGameResult>) {
        playerDtos.forEach { printResultByPlayer(it) }
        println()
        println("## 최종 수익")
        playerDtos.forEach { printWinnings(it) }
    }

    private fun printResultByPlayer(playerDto: PlayerGameResult) {
        print("${playerDto.name}카드: ")
        print(playerDto.cards.joinToString(", ") { it.korean })
        println(" - 결과: ${playerDto.totalPoint.value}")
    }

    private fun printWinnings(playerDto: PlayerGameResult) {
        print("${playerDto.name}: ")
        print(playerDto.winningAmount)
        println()
    }
}
