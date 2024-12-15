package blackjack.ui

import blackjack.domain.BlackJackRule
import blackjack.domain.Dealer
import blackjack.domain.Gambler
import blackjack.domain.Participant
import blackjack.domain.Participants

object BlackJackPrinter {
    private const val PRINT_SEPARATOR = ", "

    fun askForPlayerName() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun announceCardDistribution(participants: Participants) {
        val names = participants.joinToString(separator = PRINT_SEPARATOR) { gambler -> gambler.name }
        println("${names}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardMessage(participants: Participants) {
        participants.forEach { participant ->
            printCardMessage(participant)
        }
        printLineFeed()
    }

    fun printCardMessage(participant: Participant) {
        println(createCardMessage(participant))
    }

    fun askIfWantMoreCard(participant: Participant) {
        println("${participant.name}은(는) 한장의 카드를 더 받겠습니까?(예는 ${BlackJackReader.YES_SIGN}, 아니오는 ${BlackJackReader.NO_SIGN}. 대소문자 구분 X)")
    }

    fun printResult(participants: Participants) {
        participants.forEach { participant ->
            println("${createCardMessage(participant)} - 결과: ${participant.calculateTotalScore()}")
        }
    }

    private fun createCardMessage(participant: Participant) = "${participant.name}카드: ${CardMessageCreator.create(participant.cards)}"

    fun printLineFeed() {
        println()
    }

    fun announceCanNotReceiveCard(participant: Participant) {
        when (participant) {
            is Dealer -> println("딜러는 ${Dealer.MAXIMUM_SCORE_TO_RECEIVE_CARD}점을 초과하여 카드를 받을 수 없습니다.")
            is Gambler -> println(
                """
                    |카드의 총합이 ${BlackJackRule.WIN_SCORE}을 초과하여 더이상 카드를 받을 수 없습니다.
                    |${participant.name}의 턴을 종료합니다.
                """.trimMargin()
            )
        }
        printLineFeed()
    }

    fun announceReceiveCard() {
        println("딜러는 ${Dealer.MAXIMUM_SCORE_TO_RECEIVE_CARD}점 이하라 한장의 카드를 더 받았습니다.")
        printLineFeed()
    }
}
