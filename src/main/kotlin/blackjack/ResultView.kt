package blackjack

fun getPlayerCardText(player: Player): String {
    val cardTexts = player.cardDeck.cards.map { getCardNumberText(it.cardNumber) + getCardTypeText(it.type) }
    return "${player.name}카드: ${cardTexts.joinToString(", ")}"
}

fun getCardTypeText(cardType: CardType): String {
    return when (cardType) {
        CardType.SPADE -> "스페이드"
        CardType.CLOVER -> "클로버"
        CardType.HEART -> "하트"
        CardType.DIAMOND -> "다이아"
    }
}

fun getCardNumberText(cardNumber: CardNumber): String {
    return when (cardNumber) {
        CardNumber.ACE -> "A"
        CardNumber.TWO -> "2"
        CardNumber.THREE -> "3"
        CardNumber.FOUR -> "4"
        CardNumber.FIVE -> "5"
        CardNumber.SIX -> "6"
        CardNumber.SEVEN -> "7"
        CardNumber.EIGHT -> "8"
        CardNumber.NINE -> "9"
        CardNumber.TEN -> "10"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
    }
}
