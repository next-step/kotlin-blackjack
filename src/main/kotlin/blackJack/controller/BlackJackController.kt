package blackJack.controller

import blackJack.domain.CardDeck
import blackJack.domain.Dealer
import blackJack.domain.Player
import blackJack.domain.Players
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto
import blackJack.view.InputView
import blackJack.view.OutputView

fun main() {
    val cardDeck = CardDeck.createShuffledDeck()
    val dealer = Dealer(cardDeck)

    OutputView.printEnterName()
    val inputNames = InputView.inputNames()
    val playerList = Player.splitNames(inputNames)
    OutputView.printPlayer(playerList)

    val players: Players = Players.createPlayers(playerList, dealer)
    val playersDto = PlayersDto(players)
    OutputView.printPlayerCards(playersDto)

//    val finishGamePlayers = players.players.map { player ->
//        playGame(player, dealer)
//    }

//    val playersResult = PlayersDto(Players(finishGamePlayers))
//    OutputView.printResult(playersResult)
}

// private fun playGame(player: Player, dealer: Dealer): Player {
//    var currentPlayer = player
//
//    while (isContinueGame(currentPlayer)) {
//        currentPlayer.addCard(dealer)
//        OutputView.printPlayerCard(PlayerDto(currentPlayer))
//    }
//    OutputView.printPlayerCard(PlayerDto(currentPlayer))
//    return currentPlayer
// }

private fun isContinueGame(currentPlayer: Player): Boolean {
    if (!currentPlayer.isHit()) {
        return false
    }

    val playerDto = PlayerDto(currentPlayer)
    OutputView.printQuestionYesOrNo(playerDto)
    val answer = InputView.answerYesOrNo()
    return answer == "y"
}
