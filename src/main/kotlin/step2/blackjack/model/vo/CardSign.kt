package step2.blackjack.model.vo

@JvmInline
value class CardSign(val sign: String) {

    init {
        val signValueList = Sign.values().map { it.value }
        require(signValueList.contains(sign)) {
            "카드 기호는 ${signValueList}만 사용 가능합니다."
        }
    }

    enum class Sign(val value: String) {
        SPACE(SPACE_VALUE), CLOVER(CLOVER_VALUE), HEART(HEART_VALUE), DIAMOND(DIAMOND_VALUE)
    }

    companion object {
        private const val SPACE_VALUE = "스페이드"
        private const val CLOVER_VALUE = "클로버"
        private const val HEART_VALUE = "하트"
        private const val DIAMOND_VALUE = "다이아"

        /**
         * 스트링 값으로 카드 기호 생성
         * */
        fun from(value: String): CardSign {
            return CardSign(value)
        }

        /**
         * 랜덤으로 카드 기호 생성
         * */
        fun random(): CardSign {
            return CardSign(Sign.values().random().value)
        }
    }
}
