package blackjack.view

import blackjack.Card
import blackjack.Player

private const val ZERO_COUNT = 0

object OutputView {
    fun printFirstDeal(players: List<String>) {
        println("딜러와 ${players.joinToString(", ")} 에게 2장의 카드를 나누었습니다.")
    }

    fun printCardsByPlayer(playerDtos: List<PlayerDto>) {
        playerDtos.forEach { printCardsByPlayer(it) }
    }

    fun printCardsByPlayer(playerDto: PlayerDto) {
        print("${playerDto.name}카드: ")
        println(playerDto.cards.joinToString(", ") { it.korean })
    }

    fun printHitDealerResult(count: Int) {
        if (count > ZERO_COUNT) {
            println("딜러는 16이하라 ${count}장의 카드를 더 받았습니다.")
        }
    }

    fun printResult(playerDtos: List<PlayerDto>) {
        playerDtos.forEach { printResultByPlayer(it) }
        println()
        println("## 최종 승패")
        playerDtos.forEach { printWinnings(it) }
    }

    private fun printResultByPlayer(playerDto: PlayerDto) {
        print("${playerDto.name}카드: ")
        print(playerDto.cards.joinToString(", ") { it.korean })
        println(" - 결과: ${playerDto.totalPoint}")
    }

    private fun printWinnings(playerDto: PlayerDto) {
        print("${playerDto.name}: ")
        if (playerDto.winningCount != ZERO_COUNT) {
            print("${playerDto.winningCount}승 ")
        }
        if (playerDto.losingCount != ZERO_COUNT) {
            print("${playerDto.losingCount}패 ")
        }
        println()
    }
}

data class PlayerDto(
    val name: String,
    val cards: Set<Card>,
    val totalPoint: Int,
    val winningCount: Int = ZERO_COUNT,
    val losingCount: Int = ZERO_COUNT,
) {
    constructor(player: Player) : this(
        player.name,
        player.playingCards.cards,
        player.playingCards.calculatePoint().value,
        player.winningCount,
        player.losingCount,
    )
}
