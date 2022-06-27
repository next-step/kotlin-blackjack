package blackjack.model.candidate

import blackjack.model.card.Card

class Dealer(
    name: CandidateName = CandidateName(DEALER_NAME),
) : Candidate(candidateName = name) {

    override val isDealer
        get() = true

    override fun receiveCard(card: Card) {
        cards.addOne(card)
        needMoreCard = needMoreCard()
    }

    private fun needMoreCard() =
        cardSize == DETERMINABLE_CARD_COUNT_FOR_NEED_MORE_CARD && cards.sumOfScore.isOneOfScoreEqualOrLessThan(
            BOUNDARY_SCORE_FOR_RECEIVING_MORE_CARD
        )

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DETERMINABLE_CARD_COUNT_FOR_NEED_MORE_CARD = 2
        const val BOUNDARY_SCORE_FOR_RECEIVING_MORE_CARD = 16
    }
}
