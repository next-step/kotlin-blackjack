package blackjack_dealer.domain

import blackjack_dealer.entity.GamerCurrentState

data class Participant(
    private val name: String,
    private val cards: GamerCards,
    private val currentState: GamerCurrentState,
)
