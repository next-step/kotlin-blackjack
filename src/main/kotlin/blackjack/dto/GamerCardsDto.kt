package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class GamerCardsDto(
    val dealerFistCard: String,
    val players: List<PlayerDto>
) {
    constructor(dealer: Dealer, players: List<Player>) : this(
        CardView(dealer.state.cards.elements.first()).value,
        players.map(::PlayerDto)
    )
}
