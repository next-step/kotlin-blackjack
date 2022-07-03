package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.participant.GameResult

interface State {

    fun receiveCard(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun cards(): List<Card>

    fun score(): Score

    fun judgementGameResult(otherScore: Score): GameResult
}
