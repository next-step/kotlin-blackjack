package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.user.User
import blackjack.ui.InputView.readInputForMoreCard
import blackjack.ui.InputView.readInputForPlayer
import blackjack.ui.ResultView
import blackjack.ui.ResultView.printAskTakeMoreCard

fun main() {
    val playerNameList = getPlayerNameList()
    val blackjackGame = initBlackjackGame(playerNameList)
    playBlackjackGame(blackjackGame)
    blackjackGame.getPlayerList().forEach(ResultView::printPlayerResult)
}

fun getPlayerNameList(): List<String> {
    ResultView.printRequestPlayerNames()
    return readInputForPlayer()
}

fun initBlackjackGame(playerNameList: List<String>): BlackjackGame {
    val blackjackGame = BlackjackGame(playerNameList)
    ResultView.printAddCardsForInit(playerNameList.joinToString(", "))
    blackjackGame.getPlayerList()
        .forEach(ResultView::printPlayerHand)
    return blackjackGame
}

fun playBlackjackGame(blackjackGame: BlackjackGame) {
    blackjackGame
        .getPlayerList()
        .forEach(blackjackGame::checkUserCardAddable)
}

fun BlackjackGame.checkUserCardAddable(user: User) {
    if (user.isHandAddable())
        takeMoreCard(user)
}

fun BlackjackGame.takeMoreCard(user: User) {
    printAskTakeMoreCard(user)
    val result = readInputForMoreCard()
    if (result) {
        addCardToPlayer(user)
        ResultView.printPlayerHand(user)
        return checkUserCardAddable(user)
    }
    ResultView.printPlayerHand(user)
}
