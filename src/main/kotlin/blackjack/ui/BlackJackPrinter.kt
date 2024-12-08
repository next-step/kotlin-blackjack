package blackjack.ui

import blackjack.domain.BlackJackRule
import blackjack.domain.Gambler
import blackjack.domain.Gamblers

object BlackJackPrinter {
    private const val PRINT_SEPARATOR = ", "

    fun askForPlayerName() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun announceCardDistribution(gamblers: Gamblers) {
        val names = gamblers.joinToString(separator = PRINT_SEPARATOR) { gambler -> gambler.name }
        println("${names}에게 2장의 카드를 나누었습니다.")
    }

    fun printAllGamblersCardMessage(gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            printGamblerCardMessage(gambler)
        }
        printLineFeed()
    }

    fun printGamblerCardMessage(gambler: Gambler) {
        println(createGamblerCardMessage(gambler))
    }

    fun askIfWantMoreCard(gambler: Gambler) {
        println("${gambler.name}은(는) 한장의 카드를 더 받겠습니까?(예는 ${BlackJackReader.YES_SIGN}, 아니오는 ${BlackJackReader.NO_SIGN}. 대소문자 구분 X)")
    }

    fun printResult(gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            println("${createGamblerCardMessage(gambler)} - 결과: ${gambler.calculateTotalScore()}")
        }
    }

    private fun createGamblerCardMessage(gambler: Gambler) = "${gambler.name}카드: ${createCardMessage(gambler)}"

    private fun createCardMessage(gambler: Gambler): String {
        return gambler.cards
            .joinToString(separator = PRINT_SEPARATOR) { card -> card.signature }
    }

    fun printLineFeed() {
        println()
    }

    fun announceCanNotReceiveCard(gambler: Gambler) {
        println(
            """
            |카드의 총합이 ${BlackJackRule.WIN_SCORE}을 초과하여 더이상 카드를 받을 수 없습니다.
            |${gambler.name}의 턴을 종료합니다.
        """.trimMargin()
        )
    }
}
