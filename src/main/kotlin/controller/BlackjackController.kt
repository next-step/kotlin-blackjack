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
        val dealer = Player(dealerInfo, PlayingCards(cardGenerator))
        val players = askPlayers()
        printStarted(players + dealer)
        players.forEach { play(it) }
        printResult(players + dealer)
    }

    private fun askPlayers(): Players {
        val playerInfos = InputView.askPlayerNames().map { PlayerInfo(PlayerName(it)) }
        return Players(playerInfos.map { Player(it, PlayingCards(cardGenerator)) })
    }

    private fun printStarted(players: Players) = OutputView.printStarted(PlayersDto.from(players))
    private fun printResult(players: Players) = OutputView.printResult(PlayersDto.from(players))

    private fun play(player: Player) {
        while (!player.isFinished()) {
            val draw = InputView.askDraw(player.name())
            player.play(draw, cardGenerator)
            OutputView.printPlayer(PlayerDto.from(player))
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private val dealerInfo = PlayerInfo(PlayerName(DEALER_NAME))
    }
}
