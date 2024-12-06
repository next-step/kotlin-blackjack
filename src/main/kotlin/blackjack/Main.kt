package blackjack

import blackjack.domain.BlackJackDealer
import blackjack.domain.BlackJackDeck
import blackjack.domain.BlackJackDeckGenerator
import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackNormalPlayer
import blackjack.domain.BlackJackPlayerCards
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackResultView

fun main() {
    val blackJackDeck = BlackJackDeckGenerator.getDefaultDeck()
    val playerNames = BlackJackInputView.getPlayerName()
    val blackJackGame = getGame(playerNames, blackJackDeck)
    BlackJackInputView.drawBlackJackPlayersCards(blackJackGame.players, blackJackGame.dealer)
    doGame(blackJackGame, blackJackDeck)
    drawResult(blackJackGame)
}

private fun drawResult(blackJackGame: BlackJackGame) {
    BlackJackResultView.drawBlackJackPlayersCardsWithResult(blackJackGame.players)
    BlackJackResultView.drawBlackJackDealerWithResult(blackJackGame.dealer)
    BlackJackResultView.drawGameResult(blackJackGame.getGameResult())
}

private fun doGame(
    blackJackGame: BlackJackGame,
    blackJackDeck: BlackJackDeck,
) {
    blackJackGame.players.forEach {
        while (it.drawPossible() && BlackJackInputView.getPlayerDrawCardYn(it)) {
            it.drawCard(blackJackDeck)
            BlackJackInputView.drawBlackJackNormalPlayerCards(it)
        }
    }
    if (blackJackGame.dealer.drawCard(blackJackDeck)) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}

private fun getGame(
    playerNames: List<String>,
    blackJackDeck: BlackJackDeck,
): BlackJackGame {
    return BlackJackGame(
        playerNames.map {
            BlackJackNormalPlayer(it, BlackJackPlayerCards.byDeck(blackJackDeck))
        },
        BlackJackDealer(
            blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
        ),
    )
}
