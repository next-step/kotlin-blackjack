package blackjack.model

enum class Suit(
    val code: String,
    val alias: String,
) {
    SPADE("♠", "스페이드"),
    HEART("♥", "하트"),
    DIAMOND("♦", "다이아"),
    CLOVER("♣", "클로버"),
    ;
}
