package blackjack.model

import blackjack.CardHolder

class GameDealer(override val cardHand: CardHand): CardHolder {
    override val name: String = "딜러"
}
