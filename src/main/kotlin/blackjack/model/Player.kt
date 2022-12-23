package blackjack.model

class Player(
    val name: String,
) {
    private val _cards = Cards()
    val cards: List<Card>
        get() = _cards

    fun addCard(card: Card) {
        check(isPickable()) { "플레이어의 점수가 이미 21을 넘었습니다" }
        _cards.add(card)
    }

    fun isPickable(): Boolean {
        return _cards.getPickableScore() < BLACKJACK_SCORE
    }

    fun getFinalScore(): Int {
        return _cards.getFinalScore()
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val DELIMITER = ","

        fun playersOf(input: String): List<Player> {
            return input.split(DELIMITER).map(::Player)
        }
    }
}
