package blackjack.domain

class Dealer : Player(PlayerName("딜러")) {

    fun shouldGetMoreCard() : Boolean {
        return getCardScore() < DEALER_SHOULD_GET_CARD_SCORE
    }

    companion object {
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
