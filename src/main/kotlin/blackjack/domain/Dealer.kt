package blackjack.domain

class Dealer(hand: Hand = Hand()) : Participant(hand) {
    val shouldAddCard: Boolean
        get() = super.score() < DEALER_HIT_SCORE

    fun additionalCard(deck: Card) {
        if (shouldAddCard) {
            receive(Deck(listOf(deck)))
        }
    }

    companion object {
        val DEALER_HIT_SCORE = Score(17)
    }
}
