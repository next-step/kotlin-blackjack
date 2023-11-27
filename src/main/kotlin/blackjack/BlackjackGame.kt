package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {
    private val players = Players(
        InputView.getNicknames().map(::Player)
    )
    private val deck = Deck()
    private val dealer = Dealer("딜러")
    private val participants = listOf(dealer) + players

    fun start() {
        dealCards()
        process()
        showGameResult()
    }

    private fun dealCards() {
        participants.forEach {
            it.receiveInitialCards(
                deck.draw(Deck.INITIAL_DEAL_SIZE)
            )
        }
        OutputView.printPlayerStates(participants, Deck.INITIAL_DEAL_SIZE)
    }

    private fun process() {
        while (players.withHit().isNotEmpty()) {
            players.withHit().first().playGame()
        }
        if (dealer.needOneMoreCard()) {
            dealer.receiveCard(deck.draw())
        }
    }

    private fun Player.playGame() {
        if (InputView.askHitOrStand(this.name)) {
            this.receiveCard(deck.draw())
            OutputView.printParticipantCards(this)
        } else {
            this.turnStand()
        }
    }

    private fun showGameResult() {
        OutputView.printGameScore(participants)
        OutputView.printDealerGameResult(GameResult.resultOfDealer(players, dealer))

        val playerGameResults = players.map { player ->
            GameResult.resultOfPlayer(player, dealer)
        }
        OutputView.printPlayerGameResult(players, playerGameResults)
    }
}

fun main() {
    val game = BlackjackGame()
    game.start()
}
