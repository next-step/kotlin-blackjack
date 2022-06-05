package blackjack.application

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.enums.Type
import blackjack.enums.Value
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
                if (player.score().couldGetMoreCard()) break
                BlackjackResultView.resultOfDraw(player)
            }
            BlackjackResultView.resultOfDraw(player)
        }
    }

    companion object {
        private const val MINIMUM_PLAYER_COUNT = 2
        private const val MAXIMUM_PLAYER_COUNT = 8

        private val types = Type.values()
        private val values = Value.values()
        private val cards = (0..51).map { Card(types[it / 13], values[it % 13]) }
        private val deck = Deck(cards.shuffled())
    }
}
