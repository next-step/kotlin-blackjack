package blackjack

import blackjack.model.CardDeck
import blackjack.model.MatchResult.LOSE
import blackjack.model.MatchResult.PUSH
import blackjack.model.MatchResult.WIN
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
        val players = initPlayers(playerNames)
        val dealer = initPlayer(DEALER_NAME)
        outputView.printInitCards(dealer, players)

        playBlackjackGame(players)
        drawDealerCardOrNot(dealer)
        outputView.printCardResult(dealer, players)

        val dealerResult = listOf(
            WIN to players.count { dealer.wins(it) == WIN },
            LOSE to players.count { dealer.wins(it) == LOSE },
            PUSH to players.count { dealer.wins(it) == PUSH }
        ).toMap()
        val playersResult = players.associateWith { it.wins(dealer) }
        outputView.printGameResult(dealerResult, playersResult)
    }

    private fun drawDealerCardOrNot(dealer: Player) {
        if (dealer.getFinalScore() > DEALER_DRAW_SCORE) {
            return
        }

        dealer.addCard(cardDeck.drawCard())
        outputView.printDealerDraw(dealer)
    }

    private fun initPlayers(names: String): Players {
        return Players(splitNames(names).map { initPlayer(it) })
    }

    private fun initPlayer(name: String): Player {
        return Player(name, cardDeck.drawCards(INIT_CARD_COUNT))
    }

    private fun splitNames(names: String): List<String> {
        return names.split(NAME_STRING_DELIMITER).map { it.trim() }
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
        private const val NAME_STRING_DELIMITER = ","
    }
}

fun main() {
    BlackjackApplication().play()
}
