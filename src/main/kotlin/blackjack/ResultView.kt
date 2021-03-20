package blackjack

fun getCardTypeText(cardType: CardType): String {
    return when (cardType) {
        CardType.SPADE -> "스페이드"
        CardType.CLOVER -> "클로버"
        CardType.HEART -> "하트"
        CardType.DIAMOND -> "다이아"
    }
}
