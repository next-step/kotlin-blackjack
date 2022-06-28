package blackjack

class BlackjackGameElement(val gamers: List<UserRole>, deck: Deck) {

    private val _cards = deck.cards.toMutableList()

    init {
        validateMinPlayer(this.gamers.size)

        repeat(BASIC_RULE_COUNT) {
            this.gamers.forEach {
                it.draw(_cards.removeAt(TOP_CARD))
            }
        }
    }

    private fun validateMinPlayer(playerCount: Int) {
        require(BLACKJACK_PLAY_MIN_PLAYER_COUNT > playerCount) { "플레이어가 너무 많습니다" }
    }

    fun draw(): Card = _cards.removeAt(TOP_CARD)

    val cards: List<Card>
        get() = _cards.toList()


    companion object {
        private const val BASIC_RULE_COUNT = 2
        private const val BLACKJACK_CARD_COUNT = 52
        private const val BLACKJACK_PLAY_MIN_PLAYER_COUNT = BLACKJACK_CARD_COUNT / BASIC_RULE_COUNT
        const val TOP_CARD = 0
    }
}
