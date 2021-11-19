package controller

import domain.card.RandomCardGenerator
import domain.player.BetAmount
import domain.player.Dealer
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
    private val dealer = Dealer(cardGenerator)

    fun run() {
        val players = askPlayers()
        printStarted(players)
        players.forEach { play(it) }
        playDealer()
        printResult(players)
    }

    private fun askPlayers(): Players {
        val playerInfos = InputView.askPlayerNames()
            .map { PlayerInfo(PlayerName(it), BetAmount(InputView.askBetAmount(it))) }
        return Players(playerInfos.map { Player(it, cardGenerator) })
    }

    private fun printStarted(players: Players) = OutputView.printStarted(PlayersDto.from(players + dealer))
    private fun printResult(players: Players) = OutputView.printResult(PlayersDto.from(players + dealer))

    private fun play(player: Player) {
        while (!player.isFinished()) {
            val draw = InputView.askDraw(player.name())
            player.play(draw, cardGenerator)
            OutputView.printPlayer(PlayerDto.from(player))
        }
    }

    private fun playDealer() {
        val drawable = dealer.isDrawable()
        OutputView.printDealerMessage(drawable)
        if (drawable) {
            dealer.draw(cardGenerator)
        }
    }
}
