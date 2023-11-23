package blackjack.model

import blackjack.view.InputView

class Player(val name: String, val cardHand: CardHand) {
    fun moreCardOrNot(askToPlayer: (name: String) -> PlayAnswer) = when (askToPlayer(name)) {
        PlayAnswer.Y -> true
        PlayAnswer.N -> false
    }
}
