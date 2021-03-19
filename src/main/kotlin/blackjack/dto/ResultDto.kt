package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.player.Player

data class ResultDto(val name: String, val cards: List<Card>, val score: Int) {
    constructor(player: Player) : this(player.name, player.cards.elements, player.cards.score.value)
}
