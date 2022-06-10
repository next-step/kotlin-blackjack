package blackjack.domain.game

class TakeMoreDealer : TakeMoreDealerStrategy {

    override fun canBeTakeOneCard(score: Int): Boolean {
        return score <= DEALER_TAKE_ONE_CARD_POLICY
    }

    companion object {
        const val DEALER_TAKE_ONE_CARD_POLICY = 16
    }
}
