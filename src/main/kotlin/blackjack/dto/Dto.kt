package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.player.Player

data class Dto(val name: String, val cards: List<Card>) {
    constructor(player: Player) : this(player.name, player.cards.elements)
}
