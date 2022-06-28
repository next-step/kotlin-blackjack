package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.player.Dealer

object DealerActionProducer {

    const val DEALER_STAY_NUMBER = 17

    fun produce(dealer: Dealer): Action {
        return if (Score.of(dealer.hand) < Score.of(DEALER_STAY_NUMBER)) Action.HIT
        else Action.STAY
    }
}
