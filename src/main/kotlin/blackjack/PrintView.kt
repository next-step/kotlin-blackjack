package blackjack

object PrintView {
    private const val INPUT_USER_DESC = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val OFFER_INITIAL_CARDS_POSTFIX = "에게 $START_CARD_NUM 장 나누었습니다."
    private const val SEPARATOR = ", "
    private const val OFFERED_CARD_NAME_FORMAT = "%s카드: "

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

    private fun printCards(cards: List<Card>) {
        val cardJoint = cards.joinToString(separator = SEPARATOR)
        println(cardJoint)
    }

    fun printOfferedCardsWithName(name: String, cards: List<Card>) {
        printName(name)

        printCards(cards)
    }
}
