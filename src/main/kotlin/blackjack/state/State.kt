package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerDeck
import blackjack.domain.card.Score

const val BLACKJACK_NUMBER = 21

sealed interface State {
    fun currentCard(): PlayerDeck
    fun isFinish(): Boolean
    fun draw(card: Card): State
    fun score(playerDeck: PlayerDeck): Int {
        return Score(playerDeck).getScore()
    }
}
