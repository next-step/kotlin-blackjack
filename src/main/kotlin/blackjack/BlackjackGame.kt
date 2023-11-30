package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {
    private val deck = Deck()
    private val dealer = Dealer("딜러")
    private val players = Players(
        InputView.getNicknames().map(::Player)
    )
    private val participants = Participants(dealer, players)

    fun start() {
        dealCards()
        process()
        showGameResult()
    }

    private fun dealCards() {
        participants.getAll().forEach {
            it.receiveInitialCards(
                deck.draw(Deck.INITIAL_DEAL_SIZE)
            )
        }
        OutputView.printDealingHeader(players.getNames())
        OutputView.printParticipantCards(participants)
    }

    private fun process() {
        while (players.withHit().isNotEmpty()) {
            players.withHit().first().playGame()
        }
        if (dealer.canReceiveOneMoreCard()) {
            dealer.receiveCard(deck.draw())
            OutputView.printDealerReceiveMessage()
        }
    }

    private fun Player.playGame() {
        if (InputView.askHitOrStand(this.name)) {
            this.receiveCard(deck.draw())
            OutputView.printPlayerCards(this.name, this.cards)
        } else {
            this.turnStand()
        }
    }

    private fun showGameResult() {
        OutputView.printGameScore(participants)
        OutputView.printDealerGameResult(dealer versus players)

        val playerGameResults = players.map { player ->
            player versus dealer
        }
        OutputView.printPlayerGameResult(players, playerGameResults)
    }
}

fun main() {
    val game = BlackjackGame()
    game.start()
}
