package card
enum class Suit(val koName: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클러버");

    companion object {

        private val suitList = listOf(SPADE, HEART, DIAMOND, CLUB)
        fun Suit.getName(): String {
            return this.koName
        }

        fun getSuitList() = suitList
    }
}
