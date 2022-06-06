package blackjack.application.dto

import blackjack.domain.RoundResults
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.player.vo.Name

data class BlackJackResult(
    val name: Name,
    val cards: List<Card>,
    val score: Score
)

data class BlackjackResults(
    val results: List<BlackJackResult>
)
