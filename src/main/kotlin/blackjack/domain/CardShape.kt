package blackjack.domain

enum class CardShape(val displayName: Name) {
    HEART(Name("하트")),
    CLUB(Name("클로버")),
    DIAMOND(Name("다이아몬드")),
    SPADE(Name("스페이드")),
    ;
}
