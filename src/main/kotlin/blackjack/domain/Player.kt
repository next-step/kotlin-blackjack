package blackjack.domain

class Player(val name: String) {
    val cardDeck = CardDeck.empty()
    var point = 0
        private set

    init {
        require(name.isNotBlank()) { "Player 이름은 필수 입력입니다." }
    }

    fun hit(card: Card) {
        cardDeck.add(card.copy())
        point = point()
    }

    private fun point(): Int =
        cardDeck.cards.fold(0) { acc, card ->
            val totalPoint = acc + card.number.value

            if (card.number.isAce()) {
                val maxValue = acc + card.number.orValue
                val cardPoint = if (maxValue < CardDeck.BLACK_JACk_NUMBER) card.number.orValue else card.number.value

                return@fold acc + cardPoint
            }

            totalPoint
        }

    fun isBust(): Boolean = point > CardDeck.BLACK_JACk_NUMBER

    companion object {
    }
}
