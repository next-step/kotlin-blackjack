package blackjack.view

import blackjack.domain.Card
import blackjack.domain.START_CARD_NUM

object PrintView {
    private const val INPUT_USER_DESC = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val OFFER_INITIAL_CARDS_POSTFIX = "에게 $START_CARD_NUM 장 나누었습니다."
    private const val SEPARATOR = ", "
    private const val OFFERED_CARD_NAME_FORMAT = "%s카드: "
    private const val RESULT_FORMAT = " - 결과: %d"
    private const val ASK_ONE_MORE_CARD_FORMAT = "%s 은 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"

    fun printInputUserNamesDesc() {
        println(INPUT_USER_DESC)
    }

    fun printOfferInitialCardsWithNames(names: List<String>) {
        val namesFormat = names.joinToString(separator = SEPARATOR)

        println("$namesFormat$OFFER_INITIAL_CARDS_POSTFIX")
    }

    private fun printName(name: String) {
        val nameMessage = OFFERED_CARD_NAME_FORMAT.format(name)
        print(nameMessage)
    }

    private fun printCards(cards: List<Card>, lineFeed: Boolean) {
        val cardJoint = cards.joinToString(separator = SEPARATOR)
        print(cardJoint)
        if (lineFeed) println()
    }

    fun printHaveCardsWithName(name: String, cards: List<Card>, lineFeed: Boolean = true) {
        printName(name)

        printCards(cards, lineFeed)
    }

    fun askOneMoreCard(name: String) {
        println(ASK_ONE_MORE_CARD_FORMAT.format(name))
    }

    fun printResultSum(sum: Int) {
        println(RESULT_FORMAT.format(sum))
    }
}
