package step2.blackjack.model.vo

/**
 * 카드 숫자
 * */
data class CardNumber(val cardNumber: Triple<String, Int, Int?>) {

    enum class Number(val mName: String, val value: Int, val secretValue: Int?) {
        ACE("A", 1, 11),
        TWO("2", 2, null),
        THREE("3", 3, null),
        FOUR("4", 4, null),
        FIVE("5", 5, null),
        SIX("6", 6, null),
        SEVEN("7", 7, null),
        EIGHT("8", 8, null),
        NINE("9", 9, null),
        TEN("10", 10, null),
        JACK("J", 10, null),
        QUEEN("Q", 10, null),
        KING("K", 10, null);
    }

    companion object {

        /**
         * 스트링 값으로 카드 숫자 생성
         * */
        fun from(numberName: String): CardNumber {

            require(Number.values().map { it.mName }.contains(numberName)) {
                "카드 숫자로 올바른 값을 입력 해야합니다."
            }

            val number = Number.values().first { it.mName == numberName }
            return CardNumber(Triple(number.mName, number.value, number.secretValue))
        }

        /**
         * 랜덤으로 카드 숫자 생성
         * */
        fun random(): CardNumber {
            val number = Number.values().random()
            return CardNumber(Triple(number.mName, number.value, number.secretValue))
        }
    }
}
