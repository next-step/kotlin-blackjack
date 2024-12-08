package blackjack.application

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.presentation.InputView
import blackjack.presentation.OutputView

class BlackjackGame(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val deck: Deck = Deck(),
) {
    fun start() {
        val names = inputView.inputNames()
        val dealer = Dealer()
        val players = names.map(::Player)

        initialDraw(players + dealer)
        players.forEach(::progress)

        endGame(players, dealer)
    }

    private fun initialDraw(players: List<Participant>) {
        players.forEach { player ->
            player.initialDraw(deck)
        }
        outputView.printInitialCards(players)
    }

    private fun progress(player: Player) {
        if (isHitOrStay(player)) {
            player.hit(deck)
            outputView.printPlayerCards(player)
            progress(player)
        } else {
            player.toStay()
        }
    }

    private fun isHitOrStay(player: Player): Boolean = player.isPlayable && inputView.inputHitOrStay(player.name)

    private fun endGame(
        players: List<Player>,
        dealer: Dealer,
    ) {
        val result = GameResult(players, dealer)
        outputView.printResult(result)
    }
}
