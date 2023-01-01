package blackjack.controller

import blackjack.common.Policy.INITIAL_CARD_COUNT
import blackjack.domain.CardDeck
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val players: List<Participant>,
) {
    fun initialize(): List<Participant> {
        players.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.addCard(cardDeck.hit())
            }
        }
        OutputView.printInitialCards(players)

        return players
    }

    fun execute() {
        playGame()
    }

    private fun playGame() {
        players.forEach {
            hitOrNot(it, retryOrNot(), playerCardResult())
        }

        OutputView.printGameResult(players)
    }

    private tailrec fun hitOrNot(
        player: Participant,
        retryFunc: (player: Participant) -> Boolean,
        printFunc: (player: Participant) -> Unit,
    ) {
        if (player.isBust())
            return

        if (!retryFunc(player))
            return

        player.addCard(cardDeck.hit())
        printFunc(player)
        hitOrNot(player, retryFunc, printFunc)
    }

    private fun retryOrNot() = { player: Participant -> InputView.hitOrNot(player.name) }

    private fun playerCardResult() = OutputView::printPlayerCards
}
