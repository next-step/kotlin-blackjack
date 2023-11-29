package blackjack.view.dto

import blackjack.domain.card.Card
import blackjack.domain.result.game.VictoryStatues

data class FinalDealerStateDto(
    val cards: List<Card>,
    val cardScore: Int,
    val victoryStatus: VictoryStatues,
)
