package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Deck
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
            initialCard = BlackjackRule.initialCard
        )

        playGame()
    }

    private fun getPlayers(): List<Player> {
        return inputView.getPlayerNames().map {
            Player(name = it, deck.popMany(count = BlackjackRule.initialCard))
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
