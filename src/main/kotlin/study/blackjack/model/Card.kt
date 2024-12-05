package study.blackjack.model

/**
 * @author 이상준
 */
data class Card(
    val suit: Suit,
    val number: Int,
) {
    init {
        require(number in MIN_CARD_NUMBER..MAX_CARD_NUMBER) { ERROR_CARD_NUMBER_MESSAGE }
    }

    fun name(): String {
        val playingCard = PlayingCards.findPlayingCard(this.number)
        return "${playingCard.cardName}${suit.suitName}"
    }

    fun score(isAce: Boolean = true): Int {
        return PlayingCards.findPlayingCard(this.number).score(isAce)
    }

    companion object {
        private const val ERROR_CARD_NUMBER_MESSAGE = "카드 숫자는 1부터 13까지 가능합니다."
        private const val MIN_CARD_NUMBER = 1
        private const val MAX_CARD_NUMBER = 13
    }
}
