package blackjack.domain

import blackjack.model.PlayerProfit
import blackjack.model.PlayerProfits

class Game(
    val players: Players,
    val dealer: Dealer
) {
    init {
        dealer.shuffle()
        drawInitialCards()
    }

    private fun drawInitialCards() {
        players.drawInitialCards(dealer)
        repeat(INITIAL_CARDS_COUNT) {
            dealer.deliverCard().let(dealer.play::draw)
        }
    }

    fun profits(): PlayerProfits {
        val playerProfits = players.allPlayerProfits(dealer)
            .value
            .filterIsInstance<PlayerProfit.Player>()
        val dealerProfit = dealer.profit(playerProfits)
        return PlayerProfits(listOf(dealerProfit) + playerProfits)
    }

    fun playPlayers(hit: (Player) -> Boolean, printResult: (Player) -> Unit) {
        require(players.allReadyToPlay()) { "모든 플레이어가 게임을 시작할 준비가 되어야 합니다." }
        players.playGame(dealer, hit, printResult)
    }

    fun playDealer(printHit: () -> Unit) {
        require(dealer.play.shouldBeReadyToPlay()) { "딜러가 게임을 시작할 준비가 되어야 합니다." }
        while (!dealer.play.finished) {
            printHit()
            dealer.play.draw(dealer.deliverCard())
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
