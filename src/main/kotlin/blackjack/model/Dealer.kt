package blackjack.model

class Dealer : Gamer() {

    fun checkIfGetExtraCardOrNot(): Boolean =
        myReceivedCard.size == 2 && totalPoints <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
    }
}
