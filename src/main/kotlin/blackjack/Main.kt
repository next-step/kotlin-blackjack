package blackjack

import blackjack.domain.BlackJackDeck
import blackjack.domain.BlackJackDeckGenerator
import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackPlayer
import blackjack.domain.BlackJackPlayerCards
import blackjack.view.BlackJackView

fun main() {
    val blackJackDeck = BlackJackDeckGenerator.getDefaultDeck()
    val playerNames = BlackJackView.getPlayerName()
    val blackJackPlayers = getPlayers(playerNames, blackJackDeck)
    BlackJackView.drawBlackJackPlayersCards(blackJackPlayers)
    doGame(blackJackPlayers, blackJackDeck)
    BlackJackView.drawBlackJackPlayersCardsWithResult(blackJackPlayers)
    BlackJackView.drawWinPlayer(blackJackPlayers)
}

private fun doGame(
    blackJackGame: BlackJackGame,
    blackJackDeck: BlackJackDeck,
) {
    blackJackGame.players.forEach {
        while (it.isDrawPossible() && BlackJackView.getPlayerDrawCardYn(it)) {
            it.drawCard(blackJackDeck.draw())
            BlackJackView.drawBlackJackPlayerCards(it)
        }
    }
}

private fun getPlayers(
    playerNames: List<String>,
    blackJackDeck: BlackJackDeck,
): BlackJackGame {
    val blackJackGame =
        BlackJackGame(
            playerNames.map {
                BlackJackPlayer(
                    it,
                    BlackJackPlayerCards(mutableListOf(blackJackDeck.draw(), blackJackDeck.draw())),
                )
            },
        )
    return blackJackGame
}
