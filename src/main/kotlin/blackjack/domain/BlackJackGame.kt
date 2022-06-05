package blackjack.domain

import blackjack.view.GameView
import blackjack.view.InputView

data class BlackJackGame(
    val players: List<Participant>,
    val cardDeck: Deck
) {
    fun firstCardDistribution() {
        players.forEach { participant ->
            participant.addFirstCard()
        }
    }

    fun suggestMoreCardToEachPlayer() {
        players.forEach {
            while (InputView.needMoreCard(it)) {
                it.addCard(cardDeck.draw())
                GameView.displayPlayerCard(it)
            }
        }
    }

    private fun Participant.addFirstCard() {
        repeat(FIRST_DISTRIBUTION_CARD_COUNT) {
            addCard(cardDeck.draw())
        }
    }

    companion object {
        fun of(playerNames: List<String>, deck: Deck): BlackJackGame {
            return BlackJackGame(
                playerNames.map { Participant.of(it) }, deck
            )
        }

        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
