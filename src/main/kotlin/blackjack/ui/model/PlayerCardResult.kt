package blackjack.ui.model

import blackjack.domain.Dealer
import blackjack.domain.Player

class PlayerCardResult(
    player: Player
) {
    val name: String = player.name
    val cardNames: List<String> = player.cardNames
    val point: Int = player.cardPointSum()

    constructor(dealer: Dealer) : this(dealer.player)
}
