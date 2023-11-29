package blackjack.domain

enum class Suit(val value: String) {
    SPADE("스페이드"), DIAMOND("다이아몬드"), HEART("하트"), CLUB("클로버");

    companion object {
        private val SUIT_SET = values().associateBy { it.value }

        fun getSuitSet(): Map<String, Suit> {
            return SUIT_SET
        }
    }
}
