package view

import domain.Dealer
import domain.Player

interface OutputView {
    fun showGameState(players: List<Player>, dealer: Dealer)
    fun showPlayerResults(players: List<Player>, dealer: Dealer)
    fun showFinalResults(players: List<Player>, dealer: Dealer, dealerProfit: Int)
    fun showInitialCards(players: List<Player>, dealer: Dealer)
}
