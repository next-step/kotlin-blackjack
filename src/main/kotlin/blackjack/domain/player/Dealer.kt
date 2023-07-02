package blackjack.domain.player

class Dealer(name: String = "딜러") : BlackJackPlayer(PlayerName(name)) {

    private val resultStateCount = hashMapOf<GameResultState, Int>()

    val hasGetMoreCard
        get() = cards.hasMoreCard(Players.START_CARD_COUNT)

    fun shouldGetMoreCard(): Boolean {
        return cards.hasLessScore(DEALER_SHOULD_GET_CARD_SCORE)
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
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
