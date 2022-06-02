package blackjack.ui.dto

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.player.vo.Name

data class PlayerStatus(
    val name: Name,
    val cards: List<Card>,
    val score: Score
)
