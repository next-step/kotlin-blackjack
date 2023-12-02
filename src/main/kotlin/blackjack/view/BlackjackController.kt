package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.result.BlackjackResult

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

        resultView.showResult(dealer = dealer, players = players)

        resultView.showCompareResultTitle()

        resultView.showCompareResult(BlackjackResult(dealer, players))
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

        while (dealer.canDraw()) {
            dealer.draw(deck = deck)
            resultView.showDealerDraw()
        }
    }
}
