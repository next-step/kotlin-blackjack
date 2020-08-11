package blackjack.model

class Dealer : Gamer() {

    override fun requestCard(card: Card) {
        super.requestCard(card)
        requestOneOfCardIfAvailable()
    }

    private fun requestOneOfCardIfAvailable() {
        if (checkIfGetExtraCardOrNot()) super.requestCard(Card.pop())
    }

    private fun checkIfGetExtraCardOrNot(): Boolean =
        myReceivedCard.size == 2 && totalPoints <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
    }
}
