package blackjack.domain.blackjackgame

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerDeck
import blackjack.domain.judge.Judgement
import blackjack.domain.state.Ready
import blackjack.domain.state.State

class GameStatus(val state: State = Ready(PlayerDeck()), val judgements: MutableList<Judgement> = mutableListOf()) {

    fun isWinner(): Boolean = judgements.count { it.isWinner() } > 0

    fun getScore(): Int = state.score(state.currentCard())

    fun getCards(): List<Card> = state.currentCard().cards
}
