package blackjack.dto

import blackjack.domain.player.Player

data class PlayerDto(val name: String, val cards: List<String>) {
    constructor(player: Player) : this(player.name, player.state.cards.toView())
}
