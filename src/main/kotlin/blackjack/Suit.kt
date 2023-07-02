package blackjack

enum class Suit(val korName: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아"),
    CLUB("클로버");

    companion object {
        fun of(cardString: String): Suit {
            return values().find { it.korName in cardString } ?: throw IllegalArgumentException("옳지 않는 카드 이름 입니다.")
        }
    }
}
