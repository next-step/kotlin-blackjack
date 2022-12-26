package blackjack.model

class Player(
    val name: String,
    initCards: Cards = Cards()
) {
    var cards = initCards

    fun addCard(card: Card) {
        check(isPickable()) { "플레이어의 점수가 이미 21을 넘었습니다" }
        cards = Cards(cards.plus(card))
    }

    fun isPickable(): Boolean {
        return cards.getPickableScore() < BLACKJACK_SCORE
    }

    fun getFinalScore(): Int {
        return cards.getFinalScore()
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val DELIMITER = ","

        fun playersOf(input: String): List<Player> {
            return input.split(DELIMITER).map(::Player)
        }
    }
}
