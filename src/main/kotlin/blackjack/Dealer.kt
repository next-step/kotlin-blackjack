package blackjack

class Dealer(name: String = "딜러") : Gamer(name) {

    fun checkIfExtraCardOrNot(): Boolean {
        val isTwoCard = myCards.size == Constant.FIRST_HAVE_NUMBER_OF_CARD
        val isAvailableAnotherCard = calculatePoint() <= EXTRA_CARD_AVAILABLE_LIMIT_POINT
        return isTwoCard && isAvailableAnotherCard
    }

    fun requestCardIfPossibleExtraCard(card: Card) {
        if (!checkIfExtraCardOrNot()) return
        requestCard(card)
    }

    companion object {
        private val EXTRA_CARD_AVAILABLE_LIMIT_POINT = Point(16)
    }
}
