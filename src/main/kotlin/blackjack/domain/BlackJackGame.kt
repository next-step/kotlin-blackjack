package blackjack.domain

import blackjack.view.GameView
import blackjack.view.InputView

data class BlackJackGame(
    val players: List<Participant>
) {
    fun firstCardDistribution() {
        players.forEach { participant ->
            participant.addFirstCard()
        }
    }

    fun suggestMoreCardToEachPlayer() {
        players.forEach {
            while (InputView.needMoreCard(it)) {
                it.addCard()
                GameView.displayPlayerCard(it)
            }
        }
    }


    companion object {
        fun of(playerNames: List<String>): BlackJackGame {
            val cardDeck = CardDeck(Card.createDeck())
            return BlackJackGame(
                playerNames.map { Participant.of(it, cardDeck) }
            )
        }
    }
}
