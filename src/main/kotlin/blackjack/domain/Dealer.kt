package blackjack.domain

val DEALER_NAME = EntrantName("딜러")

class Dealer : Player(DEALER_NAME, Hand()) {
    val shouldAddCard: Boolean
        get() = super.score() < DEALER_HIT_SCORE

    fun additionalCard(deck: Card) {
        if (shouldAddCard) {
            receive(Deck(listOf(deck)))
        }
    }

    companion object {
        const val DEALER_HIT_SCORE = 17
    }
}
