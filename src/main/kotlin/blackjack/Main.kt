package blackjack

import blackjack.domain.BlackJackDealer
import blackjack.domain.BlackJackDeck
import blackjack.domain.BlackJackDeckGenerator
import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackPlayer
import blackjack.domain.BlackJackPlayerCards
import blackjack.view.BlackJackResultView
import blackjack.view.BlackJackInputView

fun main() {
    val blackJackDeck = BlackJackDeckGenerator.getDefaultDeck()
    val playerNames = BlackJackInputView.getPlayerName()
    val blackJackGame = getGame(playerNames, blackJackDeck)
    BlackJackInputView.drawBlackJackPlayersCards(blackJackGame)
    doGame(blackJackGame, blackJackDeck)
    BlackJackResultView.drawBlackJackResult(blackJackGame)
}

private fun doGame(
    blackJackGame: BlackJackGame,
    blackJackDeck: BlackJackDeck,
) {
    blackJackGame.players.forEach {
        while (it.isDrawPossible() && BlackJackInputView.getPlayerDrawCardYn(it)) {
            it.drawCard(blackJackDeck.draw())
            BlackJackInputView.drawBlackJackPlayerCards(it)
        }
    }
    while (blackJackGame.dealer.isDrawPossible()) {
        blackJackGame.dealer.drawCard(blackJackDeck.draw())
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}

private fun getGame(
    playerNames: List<String>,
    blackJackDeck: BlackJackDeck,
): BlackJackGame {
    val blackJackGame =
        BlackJackGame(
            playerNames.map {
                BlackJackPlayer(it, BlackJackPlayerCards.byDeck(blackJackDeck))
            },
            BlackJackDealer(
                blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
            ),
        )
    return blackJackGame
}
