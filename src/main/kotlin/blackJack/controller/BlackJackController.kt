package blackJack.controller

import blackJack.domain.Player
import blackJack.domain.Players
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto
import blackJack.view.InputView
import blackJack.view.OutputView

fun main() {
    OutputView.printEnterName()
    val inputNames = InputView.inputNames()
    val splitNames = Player.splitNames(inputNames)
    OutputView.printPlayer(splitNames)

    val players: Players = Players.initBettings(splitNames)
    val playersDto = PlayersDto(players)
    OutputView.printPlayerCards(playersDto)

    val finishGamePlayers = players.players.map { player ->
        playGame(player)
    }

    val playersResult = PlayersDto(Players(finishGamePlayers))
    OutputView.printResult(playersResult)
}

private fun playGame(player: Player): Player {
    var currentPlayer = player

    while (isContinueGame(currentPlayer)) {
        currentPlayer = currentPlayer.addCard()
        OutputView.printPlayerCard(PlayerDto(currentPlayer))
    }
    OutputView.printPlayerCard(PlayerDto(currentPlayer))
    return currentPlayer
}

private fun isContinueGame(currentPlayer: Player): Boolean {
    if (currentPlayer.cards.cards.sumOf { it.rank.score } > 21) {
        return false
    }

    val playerDto = PlayerDto(currentPlayer)
    OutputView.printQuestionYesOrNo(playerDto)
    val answer = InputView.answerYesOrNo()
    return answer == "y"
}
