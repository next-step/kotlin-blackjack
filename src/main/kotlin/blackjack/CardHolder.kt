package blackjack

import blackjack.model.CardHand
import blackjack.model.Role

interface CardHolder {
    val name: String
    val cardHand: CardHand
    val role: Role
    val id: Int
}
