package blackjack.domain

class Dealer(name: String = "딜러") : Player(PlayerName(name)) {

    private var resultStateCount = hashMapOf<GameResultState, Int>()

    val hasGetMoreCard
        get() = cards.size > Players.START_CARD_COUNT

    fun shouldGetMoreCard(): Boolean {
        return cards.getCardScore() < DEALER_SHOULD_GET_CARD_SCORE
    }

    override fun getScore(): Int {
        if(cards.getCardScore() > Cards.WIN_SCORE) {
            return ZERO_SCORE
        }
        return super.getScore()
    }

    fun match(player: Player) {
        val result = player.matchGameScore(getScore())

        val count = resultStateCount[result] ?: ZERO_COUNT
        resultStateCount[result] = count.inc()
    }

    fun getCountOfResult(state: GameResultState): Int {
        return resultStateCount[state] ?: ZERO_COUNT
    }


    companion object {
        const val ZERO_COUNT = 0
        private const val ZERO_SCORE = 0
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
