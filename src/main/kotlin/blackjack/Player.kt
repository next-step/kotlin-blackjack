package blackjack

data class Player(private val name: String, private val cards: Cards) {
    fun getName() = name
    fun getCards() = cards

    fun addMoreCards(selectValue: String, card: Card): Boolean {
        return when {
            selectValue == NO -> true
            cards.isGraterThanWinScore(card) -> throw IllegalArgumentException("21을 초과하여 카드를 입력할 수 없습니다.")
            else -> {
                cards.addCard(card)
                false
            }
        }
    }

    companion object {
        const val NO = "n"
    }
}
