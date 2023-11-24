package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {
    private val deck = Deck.of()
    private val players = Players(
        InputView.getNicknames().map(::Player)
    )

    fun start() {
        dealCards()
        process()
        showGameResult()
    }

    private fun dealCards() {
        repeat(Deck.INITIAL_DEAL_SIZE) {
            val cards = deck.draw(players.size)
            players.receiveCards(cards)
        }
        OutputView.printPlayerStates(players, Deck.INITIAL_DEAL_SIZE)
    }

    private fun process() {
        while (players.withHit().isNotEmpty()) {
            players.withHit().first().playGame(deck)
        }
    }

    private fun Player.playGame(deck: Deck) {
        if (InputView.askHitOrStand(this.name)) {
            this.getCard(deck.draw())
            OutputView.printPlayerState(this)
        } else {
            this.turnStand()
        }
    }

    private fun showGameResult() {
        players.forEach{
            OutputView.printGameScore(it, it.getScore())
        }
    }
}

fun main() {
    val game = BlackjackGame()
    game.start()
}
