package blackjack.application.dto

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.participant.vo.Name

data class BlackJackScore(
    val name: Name,
    val cards: List<Card>,
    val score: Score
)

data class BlackjackScores(
    val scores: List<BlackJackScore>
)
