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
) {
    fun start() {
        val deck = Deck()
        val (players, dealer) = setup()

        initialDraw(players + dealer, deck)
        players.forEach { player ->
            progress(player, deck)
        }
        dealerExtraDraw(dealer, deck)

        endGame(players, dealer)
    }

    private fun setup(): Participants {
        val names = inputView.inputNames()

        val players =
            names.map { name ->
                val bet = inputView.inputBet(name)
                Player(name, bet)
            }
        val dealer = Dealer()

        return Participants(players, dealer)
    }

    private fun initialDraw(
        players: List<Participant>,
        deck: Deck,
    ) {
        players.forEach { player ->
            player.initialDraw(deck)
        }
        outputView.printInitialCards(players)
    }

    private fun progress(
        player: Player,
        deck: Deck,
    ) {
        if (isHitOrStay(player)) {
            player.hit(deck)
            outputView.printPlayerCards(player)
            progress(player, deck)
        }
    }

    private fun isHitOrStay(player: Player): Boolean = inputView.inputHitOrStay(player.name)

    private fun dealerExtraDraw(
        dealer: Dealer,
        deck: Deck,
    ) {
        if (dealer.shouldDraw()) {
            dealer.hit(deck)
            outputView.printDealerExtraDraw()
        }
    }

    private fun endGame(
        players: List<Player>,
        dealer: Dealer,
    ) {
        val result = GameResult(players, dealer)
        outputView.printResult(result)
    }

    private data class Participants(
        val players: List<Player>,
        val dealer: Dealer,
    )
}
