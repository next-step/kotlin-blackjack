package blackjack.domain

class Dealer : Participant("딜러") {
    val dealerResult: DealerResult = DealerResult(false, 0, 0, 0)

    override fun canDrawMoreCard(): Boolean {
        return cards.calculateScore() <= DEALER_CAN_DRAW_MAX_SCORE
    }

    companion object {
        const val DEALER_CAN_DRAW_MAX_SCORE = 16
    }
}
