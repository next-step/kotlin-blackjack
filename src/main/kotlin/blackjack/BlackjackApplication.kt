package blackjack

import blackjack.model.CardDeck
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.impl.ConsoleInputView
import blackjack.view.impl.StandardOutputView

class BlackjackApplication(
    private val cardDeck: CardDeck = CardDeck.defaultDeck(),
    private val inputView: InputView = ConsoleInputView(),
    private val outputView: OutputView = StandardOutputView()
) {
    fun play() {
        val playerNames = inputView.readPlayers()
        val players = Players.init(playerNames, cardDeck, INIT_CARD_COUNT)
        val dealer = Player.init(DEALER_NAME, cardDeck, INIT_CARD_COUNT)
        outputView.printInitCards(dealer, players)

        playBlackjackGame(players)
        drawDealerCardOrNot(dealer)
        outputView.printCardResult(dealer, players)

        val gameResult = GameResult.of(dealer, players)
        outputView.printGameResult(players, gameResult)
    }

    private fun drawDealerCardOrNot(dealer: Player) {
        if (dealer.getFinalScore() > DEALER_DRAW_SCORE) {
            return
        }

        dealer.addCard(cardDeck.drawCard())
        outputView.printDealerDraw(dealer)
    }

    private fun playBlackjackGame(players: Players) {
        players.forEach { player ->
            pickCardsUntilStopAnswered(player)
        }
    }

    private fun pickCardsUntilStopAnswered(player: Player) {
        while (player.isPickable()) {
            if (!inputView.readPickAnswer(player)) {
                break
            }

            player.addCard(cardDeck.drawCard())
            outputView.printPlayerCards(player)
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_SCORE = 16
        private const val INIT_CARD_COUNT = 2
    }
}

fun main() {
    BlackjackApplication().play()
}
