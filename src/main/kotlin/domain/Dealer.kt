package domain

class Dealer : AbstractCardHolder() {
    fun playDealerTurn(deck: Deck) {
        while (calculateScore() < BlackjackRules.DEALER_HIT_THRESHOLD) {
            deck.drawCard()?.let { receiveCard(it) }
        }
    }
}
