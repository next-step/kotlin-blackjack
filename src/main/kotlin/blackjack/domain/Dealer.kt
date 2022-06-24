package blackjack.domain

class Dealer(
    name: String = DEALER_NAME,
    cards: List<Card> = emptyList()
) : Participant(name, cards) {
    override fun canReceive(): Boolean = score().number <= SCORE_SIXTEEN

    companion object {
        const val SCORE_SIXTEEN = 16
        private const val DEALER_NAME = "딜러"
    }
}
