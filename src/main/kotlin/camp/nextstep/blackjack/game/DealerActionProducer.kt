package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.player.Dealer

object DealerActionProducer {

    fun produce(dealer: Dealer): Action {
        return if (Score.of(dealer.hand) < Score.of(17)) Action.HIT
        else Action.STAY
    }
}
