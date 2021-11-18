package controller

import domain.card.PlayingCards
import domain.card.RandomCardGenerator
import domain.player.Player
import domain.player.PlayerInfo
import domain.player.PlayerName
import domain.player.Players
import dto.PlayerDto
import dto.PlayersDto
import view.InputView
import view.OutputView

class BlackjackController {
    private val cardGenerator = RandomCardGenerator()

    fun run() {
        val players = askPlayers()
        printStarted(players)
        players.forEach { play(it) }
        printResult(players)
    }

    private fun askPlayers() = Players(askPlayerInfos().map { Player(it, PlayingCards(cardGenerator)) })
    private fun askPlayerInfos() = InputView.askPlayerNames().map { PlayerInfo(PlayerName(it)) }
    private fun printStarted(players: Players) = OutputView.printStarted(PlayersDto.from(players))
    private fun printResult(players: Players) = OutputView.printResult(PlayersDto.from(players))

    private fun play(player: Player) {
        while (!player.isFinished()) {
            val draw = InputView.askDraw(player.name())
            player.play(draw, cardGenerator)
            OutputView.printPlayer(PlayerDto.from(player))
        }
    }
}
