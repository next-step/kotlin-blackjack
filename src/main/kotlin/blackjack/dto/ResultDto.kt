package blackjack.dto

import blackjack.domain.player.Player

data class ResultDto(val name: String, val cards: List<String>, val score: Int) {
    constructor(player: Player) : this(player.name, player.cards.toView(), player.cards.score.value)
}
