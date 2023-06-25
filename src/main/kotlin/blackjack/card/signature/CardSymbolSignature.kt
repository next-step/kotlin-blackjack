package blackjack.card.signature

enum class CardSymbolSignature(val code: String) {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    ;

    companion object {
        fun pickRandomOne() = values().toMutableList().shuffled().first()
    }
}
