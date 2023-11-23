package blackjack.model

import blackjack.CardHolder

class Player(override val name: String, override val cardHand: CardHand): CardHolder {
    fun moreCardOrNot(askToPlayer: (name: String) -> PlayAnswer) = when (askToPlayer(name)) {
        PlayAnswer.Y -> true
        PlayAnswer.N -> false
    }
}
