package blackjack.view

import blackjack.view.dto.BlackJackResultDto
import blackjack.view.dto.GamerDto
import blackjack.view.dto.GamersDto

object ConsoleOutputView {

    fun giveFirstTwoCards(gamers: GamersDto) {
        val playerNames = gamers.joinToString(",") { it.name }
        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        println()
        gamers.forEach { printGamer(it) }
    }

    fun printResult(gamers: GamersDto) {
        gamers.forEach {
            printGamer(it, lastNewLine = false)
            println(" - 결과: ${it.score}")
        }
        println()
    }

    fun printGamer(gamer: GamerDto, lastNewLine: Boolean = true) {
        val cardNames = gamer.cards.joinToString(",") { it.name }
        print("${gamer.name}카드: $cardNames")
        if (lastNewLine) {
            println()
        }
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printBlackJackResult(result: BlackJackResultDto) {
        println("### 최종 수익")
        result.gamerResults.forEach {
            println("${it.name}: ${it.profit}")
        }
    }
}
