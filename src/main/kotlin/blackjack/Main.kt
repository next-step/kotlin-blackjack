package blackjack

import blackjack.domain.BlackJackDealer
import blackjack.domain.BlackJackDeck
import blackjack.domain.BlackJackDeckGenerator
import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackPlayerCards
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackResultView

fun main() {
    val blackJackDeck = BlackJackDeckGenerator.getDefaultDeck()
    val blackJackGame = getGame(blackJackDeck)
    BlackJackInputView.drawBlackJackPlayersCards(blackJackGame.getNormalPlayers(), blackJackGame.getDealer())
    doDrawPhase(blackJackGame)
    blackJackGame.calculateResult()
    drawResult(blackJackGame)
}

private fun drawResult(blackJackGame: BlackJackGame) {
    BlackJackResultView.drawBlackJackPlayersCardsWithResult(blackJackGame.getNormalPlayers())
    BlackJackResultView.drawBlackJackDealerWithResult(blackJackGame.getDealer())
    BlackJackResultView.drawGameResult(blackJackGame.getGameResult())
    BlackJackResultView.drawGameProfit(blackJackGame.getDealer(), blackJackGame.getNormalPlayers())
}

private fun doDrawPhase(blackJackGame: BlackJackGame) {
    blackJackGame.getNormalPlayers().forEach {
        while (it.drawPossible() && BlackJackInputView.getPlayerDrawCardYn(it)) {
            it.drawCard(blackJackGame.blackJackDeck)
            BlackJackInputView.drawBlackJackPlayerCards(it)
        }
    }
    if (blackJackGame.dealerDraw()) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}

private fun getGame(blackJackDeck: BlackJackDeck): BlackJackGame {
    val playerNames = BlackJackInputView.getPlayerName()
    val players = BlackJackInputView.getPlayer(playerNames, blackJackDeck)
    return BlackJackGame(
        players,
        BlackJackDealer(
            blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
        ),
        blackJackDeck,
    )
}
