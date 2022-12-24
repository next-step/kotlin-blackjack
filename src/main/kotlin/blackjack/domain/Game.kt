package blackjack.domain

import blackjack.InputView
import blackjack.ResultView
import blackjack.model.GameResult
import blackjack.model.PlayerGameResults

class Game(
    val players: Players,
    val dealer: Dealer,
    private val resultCalculator: ResultCalculator = GameResultCalculator()
) {
    init {
        dealer.shuffle()
        drawInitialCards()
    }

    private fun drawInitialCards() {
        players.drawInitialCards(dealer)
        repeat(INITIAL_CARDS_COUNT) {
            dealer.deliverCard().let(dealer::draw)
        }
    }

    fun play() {
        playPlayers(players, dealer)
        playDealer(dealer)
        println()
    }

    fun results(): PlayerGameResults =
        resultCalculator.calculate(dealer, players)

    private fun playPlayers(players: Players, dealer: Dealer) {
        players.value
            .forEach { player ->
                while (!player.blackjack() &&
                    !player.bust() &&
                    InputView.shouldHit(player)
                ) {
                    player.hit(dealer.deliverCard())
                    ResultView.printPlayerCards(player)
                }
            }
        println()
    }

    private fun playDealer(dealer: Dealer) {
        while (!dealer.blackjack() && !dealer.stay() && !dealer.bust()) {
            InputView.printDealerHit()
            dealer.hit(dealer.deliverCard())
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
