package blackjack.domain

class Dealer(name: String = "딜러") : Player(PlayerName(name)) {

    var resultStateCount = hashMapOf<GameResultState, Int>()

    var hasGetMoreCard = false
    fun shouldGetMoreCard(): Boolean {
        hasGetMoreCard = getCardScore() < DEALER_SHOULD_GET_CARD_SCORE
        return hasGetMoreCard
    }

    fun isDealerMustLoose(): Boolean {
        return getCardScore() > WIN_SCORE
    }

    fun getCountOfResult(state: GameResultState): Int {
        return resultStateCount[state] ?: ZERO_COUNT
    }

    companion object {
        const val ZERO_COUNT = 0
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
