package blackjack.domain

class Dealer : Player(PlayerName("딜러")) {

    var resultStateCount = hashMapOf<GameResultState, Int>()

    var hasGetMoreCard = false
    fun shouldGetMoreCard(): Boolean {
        hasGetMoreCard = getCardScore() < DEALER_SHOULD_GET_CARD_SCORE
        return hasGetMoreCard
    }

    fun isDealerMustLoose(): Boolean {
        return getCardScore() > WIN_SCORE
    }

    companion object {
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
