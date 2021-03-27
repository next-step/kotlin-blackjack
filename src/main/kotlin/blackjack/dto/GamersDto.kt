package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class GamersDto(
    val dealerFistCard: String,
    val players: List<GamerDto>
) {
    constructor(dealer: Dealer, players: List<Player>) : this(
        CardView(dealer.state.cards.elements.first()).value,
        players.map(::GamerDto)
    )
}
