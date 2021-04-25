package blackjack.ui.model

import blackjack.domain.Dealer
import blackjack.domain.Player

class PlayerDto(
    player: Player
) {
    val name: String = player.name
    val cards: List<String> = player.cardNames

    constructor(dealer: Dealer) : this(dealer.player)
}
