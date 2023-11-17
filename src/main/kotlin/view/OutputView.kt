package view

import domain.Dealer
import domain.Player

interface OutputView {
    fun showGameState(players: List<Player>, dealer: Dealer)
    fun showGameResult(players: List<Player>, dealer: Dealer)
    fun showInitialCards(players: List<Player>, dealer: Dealer)
}
