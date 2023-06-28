package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

interface State {

    fun draw(card: Card): State
    fun stop(): State
    fun getCards(): Cards
    fun getPlayerGameResult(state: State): PlayerGameResult
    fun getRevenueRate(state: State): BigDecimal
}
