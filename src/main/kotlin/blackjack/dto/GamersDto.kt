package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class GamersDto(
    val dealerCards: List<String>,
    val players: List<GamerDto>
) {
    constructor(dealer: Dealer, players: List<Player>) : this(dealer.cards.toView(), players.map(::GamerDto))
}
