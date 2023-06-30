package domain.state

import domain.card.Card
import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

interface State {

    fun draw(card: Card): State
    fun stop(): State
    fun getCards(): Cards
    fun getPlayerGameResult(dealerState: State): PlayerGameResult
    fun getRevenueRate(dealerState: State): BigDecimal
}
