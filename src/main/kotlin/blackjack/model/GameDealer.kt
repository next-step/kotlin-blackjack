package blackjack.model

class GameDealer(override val id: Int, override val cardHand: CardHand): CardHolder {
    override val name: String = "딜러"
    override val role = Role.DEALER

    val isDealerShouldMoreCard: Boolean
        get() = cardHand.totalScore <= DEALER_MUST_MORE_SCORE

    companion object {
        const val DEALER_MUST_MORE_SCORE = 16
    }
}
