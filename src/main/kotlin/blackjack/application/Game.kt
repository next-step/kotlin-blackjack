package blackjack.application

import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.ui.BlackjackInputView
import blackjack.ui.BlackjackResultView

class Game(playerNames: List<String>) {
    val players = playerNames.map { Player(it, deck.draw()) }

    init {
        require(playerNames.size in MINIMUM_PLAYER_COUNT..MAXIMUM_PLAYER_COUNT) {
            "there should be a number of players from $MINIMUM_PLAYER_COUNT to $MAXIMUM_PLAYER_COUNT"
        }
    }

    fun start() {
        players.forEach { player ->
            while (BlackjackInputView.queryTakeCard(player) == "y") {
                player take deck.draw(1)
                if (player.couldGetMoreCard()) break
                BlackjackResultView.resultOfDraw(player)
            }
            BlackjackResultView.resultOfDraw(player)
        }
    }

    companion object {
        private const val MINIMUM_PLAYER_COUNT = 2
        private const val MAXIMUM_PLAYER_COUNT = 8

        private val deck = Deck(Cards.shuffled())
    }
}
