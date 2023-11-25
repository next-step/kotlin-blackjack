package model

import blackjack.dealer.Dealer
import blackjack.player.Player

data class BlackjackParticipants(
    val dealer: Dealer,
    val players: List<Player>
)
