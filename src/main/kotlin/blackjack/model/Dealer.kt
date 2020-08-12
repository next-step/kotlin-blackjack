package blackjack.model

class Dealer(name: String = "딜러") : Gamer(name) {

    var isReceivedExtraCard = false
        private set

    fun requestCardIfAvailableExtraCard() {
        if (checkIfGetExtraCardOrNot()) {
            requestCard(Card.pop())
            isReceivedExtraCard = true
        }
    }

    fun checkIfGetExtraCardOrNot(): Boolean =
        myReceivedCard.size == INIT_CARD_SIZE && totalPoints <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
        private const val INIT_CARD_SIZE = 2
    }
}
