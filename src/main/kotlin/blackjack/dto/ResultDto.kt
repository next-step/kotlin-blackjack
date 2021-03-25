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
    constructor(dealer: Dealer) : this(dealer.cards.toView(), dealer.cards.score.value)
}

data class PlayerResult(
    val name: String,
    val cards: List<String>,
    val score: Int
) {
    constructor(player: Player) : this(player.name, player.cards.toView(), player.cards.score.value)
}
