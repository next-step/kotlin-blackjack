package blackjack_dealer.domain

import blackjack_dealer.entity.GamerCurrentState

data class Dealer(
    val name: String,
    val cards: GamerCards,
    val currentState: GamerCurrentState,
)
