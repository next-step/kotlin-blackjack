package blackjack.ui.model

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player

class PlayerDto(
    player: Player
) {
    val name: String = player.name
    val cards: List<String> = player.cardNames

    constructor(name: String, cards: Set<Card>) : this(Player(name, cards))
    constructor(dealer: Dealer) : this(dealer.player)
}
