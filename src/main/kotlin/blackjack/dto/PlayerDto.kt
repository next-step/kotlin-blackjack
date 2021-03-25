package blackjack.dto

import blackjack.domain.player.Gamer

data class PlayerDto(val name: String, val cards: List<String>) {
    constructor(player: Gamer) : this(player.name, player.cards.toView())
}
