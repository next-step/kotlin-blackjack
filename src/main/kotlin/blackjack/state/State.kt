package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck
import blackjack.Score

const val BLACKJACK_NUMBER = 21

sealed interface State {
    fun currentCard(): PlayerDeck
    fun isFinish(): Boolean
    fun draw(card: Card): State
    fun score(cards: List<Card>): Int {
        return Score(cards).getScore()
    }
}
