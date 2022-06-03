package blackjack.application.dto

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.player.vo.Name

data class PlayerResult(
    val name: Name,
    val cards: List<Card>,
    val score: Score
)

data class PlayerResults(
    val results: List<PlayerResult>
)
