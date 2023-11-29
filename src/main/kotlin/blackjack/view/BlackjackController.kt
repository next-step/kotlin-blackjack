package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player

class BlackjackController(
    val inputView: InputView,
    val resultView: ResultView,
) {
    private val deck: Deck = Deck.forBlackjack()
    private val players: List<Player>

    init {
        players = getPlayers()

        resultView.showInitialPlayers(
            players = players,
            initialCard = BlackjackRule.INITIAL_CARD
        )

        playGame()

        resultView.showResult(players = players)
    }

    private fun getPlayers(): List<Player> {
        return inputView.getPlayerNames().map {
            Player(
                name = it,
                Hand(
                    cards = deck.popMany(count = BlackjackRule.INITIAL_CARD)
                )
            )
        }
    }

    private fun playGame() {
        players.forEach { player ->
            while (player.canDraw()) {
                if (inputView.getWantToDraw(player.name)) {
                    player.draw(deck = deck)
                    resultView.showPlayer(player = player)
                } else {
                    player.endTurn()
                }
            }
        }
    }
}
