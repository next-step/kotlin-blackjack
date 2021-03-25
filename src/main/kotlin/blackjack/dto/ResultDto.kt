package blackjack.dto

import blackjack.domain.player.Gamer

data class ResultDto(val name: String, val cards: List<String>, val score: Int) {
    constructor(player: Gamer) : this(player.name, player.cards.toView(), player.cards.score.value)
}
