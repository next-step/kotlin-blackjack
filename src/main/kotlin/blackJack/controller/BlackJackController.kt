package blackJack.controller

import blackJack.domain.Player
import blackJack.domain.Players
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

    OutputView.printPlayerCards(players)
}
