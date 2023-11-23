package blackjack

import blackjack.model.CardHand

interface CardHolder {
    val name: String
    val cardHand: CardHand
}
