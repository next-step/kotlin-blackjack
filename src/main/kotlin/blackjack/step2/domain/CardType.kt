package blackjack.step2.domain

enum class CardType(val value: String) {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    companion object {
        fun random(): CardType {
            return entries.random()
        }
    }
}
