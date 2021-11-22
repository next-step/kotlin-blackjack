package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Stay(override val hands: Hands) : Finish(hands)
