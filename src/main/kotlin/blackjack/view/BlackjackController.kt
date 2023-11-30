package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player

class BlackjackController(
    val inputView: InputView,
    val resultView: ResultView,
) {
    private val deck: Deck = Deck.forBlackjack()
    private val players: List<Player>
    private val dealer: Dealer

    init {
        players = getPlayers()

        dealer = Dealer(
            Hand(
                cards = deck.popMany(count = BlackjackRule.INITIAL_CARD)
            )
        )

        resultView.showInitialPlayers(
            dealer = dealer,
            players = players,
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
