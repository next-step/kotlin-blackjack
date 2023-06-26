package blackjack.model

class BlackjackDealer(
    cardDeck: CardDeck,
    private val moreCardScoreLimitConsumer: (Int) -> Unit,
) : BlackjackParticipant(PlayerName("딜러"), cardDeck) {

    private val isLessThanLimitScore: Boolean
        get() = handDeck.score <= ADD_CARD_LIMIT_SCORE

    override fun draw(cardDeck: CardDeck) {
        if (isLessThanLimitScore) {
            moreCardScoreLimitConsumer(ADD_CARD_LIMIT_SCORE)
            add(cardDeck.draw())
        }
    }

    companion object {
        private const val ADD_CARD_LIMIT_SCORE: Int = 16
    }
}
