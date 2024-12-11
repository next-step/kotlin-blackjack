package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.game.GameResult
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class GameTable(
    val deck: Deck,
    val dealer: Dealer,
    val players: List<Player>,
) {
    fun dealInitCard() =
        repeat(INIT_CARD_DRAW_COUNT) {
            dealer.hit(deck.draw())
            players.forEach { it.hit(deck.draw()) }
        }

    fun playersTurn() = players.forEach { playerTurn(it) }

    fun dealerTurn() {
        if (!dealer.canHit()) {
            return
        }
        ResultView.printDealerHit()
        dealer.hit(deck.draw())
        dealerTurn()
    }

    fun getGameResult(): GameResult = GameResult.from(dealer, players)

    private fun playerTurn(player: Player) {
        if (!player.canHit() || !InputView.inputHit(player)) {
            return
        }
        player.hit(deck.draw())
        ResultView.printPlayerCard(player)
        playerTurn(player)
    }

    companion object {
        const val INIT_CARD_DRAW_COUNT = 2
    }
}
