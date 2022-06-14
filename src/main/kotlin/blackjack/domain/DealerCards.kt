package blackjack.domain

class DealerCards : Cards() {

    override fun isHit(): Boolean {
        return score() < SCORE_TO_REQUEST_A_CARD_FOR_DEALER
    }

    companion object {
        private const val SCORE_TO_REQUEST_A_CARD_FOR_DEALER = 16
    }
}
