package blackjack.model

class Dealer(name: String = "딜러") : Gamer(name) {

    fun requestCardIfAvailableExtraCard() {
        if (checkIfGetExtraCardOrNot()) {
            requestCard(Card.pop())
        }
    }

    fun checkIfGetExtraCardOrNot(): Boolean =
        myReceivedCard.size == INIT_CARD_SIZE && totalPoints <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    fun isReceivedExtraCard() = myReceivedCard.size > INIT_CARD_SIZE

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
        private const val INIT_CARD_SIZE = 2
    }
}
