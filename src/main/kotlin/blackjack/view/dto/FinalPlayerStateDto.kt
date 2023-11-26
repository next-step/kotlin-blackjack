package blackjack.view.dto

import blackjack.domain.card.Card
import blackjack.domain.result.game.VictoryStatus

data class FinalPlayerStateDto(
    val name: String,
    val cards: List<Card>,
    val cardScore: Int,
    val victoryStatus: VictoryStatus,
)
