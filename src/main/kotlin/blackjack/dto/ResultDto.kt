package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class ResultsDto(
    val dealer: DealerResult,
    val players: List<PlayerResult>
) {
    constructor(dealer: Dealer, players: List<Player>) : this(DealerResult(dealer), players.map(::PlayerResult))
}

data class DealerResult(
    val cards: List<String>,
    val score: Int
) {
    constructor(dealer: Dealer) : this(dealer.state.cards.toView(), dealer.state.cards.score.value)
}

data class PlayerResult(
    val name: String,
    val cards: List<String>,
    val score: Int
) {
    constructor(player: Player) : this(player.name, player.state.cards.toView(), player.state.cards.score.value)
}
