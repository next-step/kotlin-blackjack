package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.participantion.Participant
import java.util.Stack

object ResultView {

    enum class Message(val context: String) {
        REQUEST_PLAYERS("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
        REQUEST_HIT("는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
        BUST(" 님의 카드 숫자의 총 수가 21을 초과하여 다음 Player 턴으로 넘어 갑니다."),
        DEALER_HIT("딜러는 16이하라 한장의 카드를 더 받았습니다."),
        INCOME("## 최종 수익");
    }

    fun printMessage(message: Message) = println(message.context)

    fun printMessage(prefix: String, message: Message) = println("${prefix}${message.context}")

    fun printHit(payers: List<String>) {
        val playNames = payers.joinToString(". ")

        println("딜러와 ${playNames}에게 2장의 나누었습니다.")
    }

    fun printState(participant: Participant) {
        val cardString: String = toCardNames(participant.cards.cardStack)

        println("${participant.name}카드: $cardString")
    }

    fun printResult(participant: Participant) {
        val cardString: String = toCardNames(participant.cards.cardStack)

        println("${participant.name}카드: $cardString - 결과: ${participant.point}")
    }

    fun printRequestPrice(playerName: String) {
        println("${playerName}의 베팅 금액은?")
    }

    fun printIncome(participant: Participant) {
        println("${participant.name}: ${participant.priceAmount}")
    }

    private fun toCardNames(cards: Stack<Card>): String =
        cards.joinToString(", ") { card -> "${card.number.symbol}${card.suit.value}" }

    fun newLine() = println()
}
