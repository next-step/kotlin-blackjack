package blackjack.controller

import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.view.CardResponse
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.PlayerResponse

class GameController {
    fun start() {
        val cardDeck = CardDeck()
        val playerNames = InputView.getPlayerNames()
        val players: List<Player> = playerNames.map { Player(it, linkedSetOf(cardDeck.pick(), cardDeck.pick())) }

        OutputView.printPlayerCards(players.map { createPlayerResponse(it) })

        players.forEach { player ->
            while (player.isNotDone()) {
                InputView.canHit(player.name).takeIf { it }
                    ?.let {
                        player.hit(cardDeck.pick())
                        OutputView.printPlayerCard(createPlayerResponse(player))
                    }
                    ?: player.stay()
            }
        }

        players.forEach { player ->
            OutputView.printPlayerResult(createPlayerResponse(player), player.cards.sum())
        }
    }

    private fun createPlayerResponse(player: Player) = PlayerResponse(
        player.name,
        player.cards.cards.map { CardResponse(it.pattern.name, it.value.name) }
    )
}
