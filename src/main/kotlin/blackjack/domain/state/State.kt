package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

interface State {
    fun cards(): Cards

    fun receiveCard(card: Card): State
    fun calculateResult(otherScore: Score): GameResult.Result
}
