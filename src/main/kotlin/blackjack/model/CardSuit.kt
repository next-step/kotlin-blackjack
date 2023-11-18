package blackjack.model

@JvmInline
value class CardSuit private constructor(val cardSuit: String){
    companion object{
        private val SUITS = listOf("스페이드", "다이아몬드", "하트", "클로버")
        private val SUITS_MAP: Map<String, CardSuit> = SUITS.associateWith {
            CardSuit(it)
        }
        private fun from(suit: String) = SUITS_MAP[suit] ?: throw IllegalArgumentException("존재하지 않는 카드 입니다")
        fun gerRandom() = from(SUITS.shuffled()[0])
    }
}
