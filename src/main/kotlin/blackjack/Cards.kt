package blackjack

data class Cards(val cards: List<Card>) {
    init {
        require(cards.toSet().size == cards.size) {
            throw IllegalArgumentException(CARDS_DUPLICATED)
        }
    }

    companion object {
        private const val CARDS_DUPLICATED = "카드가 중복되었습니다."
    }
}
