package blackjack.model

@JvmInline
value class CardValue private constructor(val cardValue: String){
    companion object{
        const val ACE_HIGH_SCORE = 11
        const val ACE_LOW_SCORE = 1

        private val VALUES = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
        private val VALUES_MAP: Map<String, CardValue> = VALUES.associateWith {
            CardValue(it)
        }
        private fun from(cardValue: String) = VALUES_MAP[cardValue] ?: throw IllegalArgumentException("존재하지 않는 카드 입니다")
        fun gerRandom() = from(VALUES.shuffled()[0])
    }
}
