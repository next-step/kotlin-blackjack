package blackjack.dto

import blackjack.domain.player.Gamer

data class GamerDto(val name: String, val cards: List<String>) {
    constructor(gamer: Gamer) : this(gamer.name, gamer.state.cards.toView())
}
