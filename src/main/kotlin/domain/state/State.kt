package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult

interface State {

    fun draw(card: Card): State
    fun stop(): State
    fun getCards(): Cards
    fun getPlayerGameResult(state: State): PlayerGameResult
    fun getBetAmount(): Int
    fun getRevenue(state: State): Int
}
