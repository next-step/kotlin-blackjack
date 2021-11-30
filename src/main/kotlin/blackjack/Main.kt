package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerList
import blackjack.domain.player.PlayerName
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

fun getPlayerNameList(): List<PlayerName> {
    ResultView.printRequestPlayerNames()
    return readInputForPlayer()
}

fun initBlackjackGame(playerNameList: List<PlayerName>): BlackjackGame {
    val blackjackGame = BlackjackGame(PlayerList.createPlayerList(playerNameList))
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

fun BlackjackGame.checkUserCardAddable(player: Player) {
    if (player.isHandAddable())
        takeMoreCard(player)
}

fun BlackjackGame.takeMoreCard(player: Player) {
    printAskTakeMoreCard(player)
    val result = readInputForMoreCard()
    if (result) {
        addCardToPlayer(player)
        ResultView.printPlayerHand(player)
        return checkUserCardAddable(player)
    }
    ResultView.printPlayerHand(player)
}
