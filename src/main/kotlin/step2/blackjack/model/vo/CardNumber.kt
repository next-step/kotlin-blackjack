package step2.blackjack.model.vo

/**
 * 카드 숫자
 * */
data class CardNumber(val cardNumber: Triple<String, Int, Int>) {

    enum class Number(val mName: String, val value: Int, val secretValue: Int) {
        ACE("A", 1, 11),
        TWO("2", 2, 0),
        THREE("3", 3, 0),
        FOUR("4", 4, 0),
        FIVE("5", 5, 0),
        SIX("6", 6, 0),
        SEVEN("7", 7, 0),
        EIGHT("8", 8, 0),
        NINE("9", 9, 0),
        TEN("10", 10, 0),
        JACK("J", 10, 0),
        QUEEN("Q", 10, 0),
        KING("K", 10, 0);
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
