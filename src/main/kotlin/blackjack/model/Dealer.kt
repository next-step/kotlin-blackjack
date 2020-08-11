package blackjack.model

class Dealer(name: String = "딜러") : Gamer(name) {

    fun checkIfGetExtraCardOrNot(): Boolean =
        myReceivedCard.size == 2 && totalPoints <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
    }
}
