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
    val playerBets = BlackJackInputView.getPlayerBets(playerNames)
    val blackJackGame = getGame(playerBets, blackJackDeck)
    BlackJackInputView.drawBlackJackPlayersCards(blackJackGame.players, blackJackGame.dealer)
    doGame(blackJackGame, blackJackDeck)
    val gameResult = blackJackGame.getGameResult()
    blackJackGame.calculateProfit(gameResult.playerResults)
    drawResult(blackJackGame)
}

private fun drawResult(blackJackGame: BlackJackGame) {
    BlackJackResultView.drawBlackJackPlayersCardsWithResult(blackJackGame.players)
    BlackJackResultView.drawBlackJackDealerWithResult(blackJackGame.dealer)
    BlackJackResultView.drawGameResult(blackJackGame.getGameResult())
    BlackJackResultView.drawGameProfit(blackJackGame.dealer, blackJackGame.players)
}

private fun doGame(
    blackJackGame: BlackJackGame,
    blackJackDeck: BlackJackDeck,
) {
    blackJackGame.players.forEach {
        while (it.drawPossible() && BlackJackInputView.getPlayerDrawCardYn(it)) {
            it.drawCard(blackJackDeck)
            BlackJackInputView.drawBlackJackPlayerCards(it)
        }
    }
    if (blackJackGame.dealerDraw(blackJackDeck)) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}

private fun getGame(
    playerBets: Map<String, Int>,
    blackJackDeck: BlackJackDeck,
): BlackJackGame {
    return BlackJackGame(
        playerBets.map {
            BlackJackNormalPlayer(it.key, BlackJackPlayerCards.byDeck(blackJackDeck), bet = it.value)
        },
        BlackJackDealer(
            blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
        ),
    )
}
