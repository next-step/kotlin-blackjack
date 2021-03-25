package blackjack.dto

import blackjack.domain.player.Gamer

data class ResultDto(val name: String, val cards: List<String>, val score: Int) {
    constructor(gamer: Gamer) : this(gamer.name, gamer.cards.toView(), gamer.cards.score.value)
}
