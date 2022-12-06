package blackjack.domain

class Player(val name: String) {
    val cardDeck = CardDeck.empty()

    init {
        require(name.isNotBlank()) { "Player 이름은 필수 입력입니다." }
    }

    fun point(): Int {
        return cardDeck.cards.fold(0) { acc, card ->
            val totalPoint = acc + card.number.value

            if (card.number == Card.Number.ACE) {
                val maxValue = acc + card.number.orValue
                val cardPoint = if (maxValue < BLACK_JACk_NUMBER) card.number.orValue else card.number.value

                return@fold acc + cardPoint
            }

            totalPoint
        }
    }

    fun isBust(): Boolean = point() > BLACK_JACk_NUMBER

    companion object {
        private const val BLACK_JACk_NUMBER = 21
    }
}
