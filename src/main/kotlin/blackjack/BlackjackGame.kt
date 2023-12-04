package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResultDecider
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.InputView.askWillHit
import blackjack.view.OutputView.printDealerGameResult
import blackjack.view.OutputView.printDealerReceiveMessage
import blackjack.view.OutputView.printDealingHeader
import blackjack.view.OutputView.printGameScore
import blackjack.view.OutputView.printParticipantCards
import blackjack.view.OutputView.printPlayerCards
import blackjack.view.OutputView.printPlayerGameResult

class BlackjackGame {
    private val deck = Deck()
    private val dealer = Dealer("딜러")
    private val players = Players(
        InputView.getNicknames().map(::Player)
    )
    private val participants = Participants(dealer, players)

    fun start() {
        setUp()
        playGame()
        showGameResult()
    }

    private fun setUp() {
        participants.receiveInitialCards { deck.draw(Deck.INITIAL_DEAL_SIZE) }
        printDealingHeader(players.getNames())
        printParticipantCards(participants)
    }

    private fun playGame() {
        while (true) {
            val player = participants.playGameByPlayer({ askWillHit(it.name) }, { deck.draw() }) ?: break
            if (!player.isStand()) printPlayerCards(player)
        }
        val dealer = participants.playGameByDealer { deck.draw() }
        if (!dealer.isStand()) printDealerReceiveMessage()
    }

    private fun showGameResult() {
        printGameScore(participants)
        val gameResults = GameResultDecider.decide(participants)
        printDealerGameResult(gameResults.dealerGameResult)
        printPlayerGameResult(gameResults.playerGameResult)
    }
}

fun main() {
    val game = BlackjackGame()
    game.start()
}
