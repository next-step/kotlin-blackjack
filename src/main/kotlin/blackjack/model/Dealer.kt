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
        myReceivedCard.size == 2 && totalPoints <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
    }
}
