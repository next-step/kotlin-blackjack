package nextstep.blackjack

import nextstep.blackjack.dto.PlayerCardDto
import nextstep.blackjack.dto.PlayerResultDto
import nextstep.blackjack.io.ConsoleInput
import nextstep.blackjack.io.ConsoleOutput

fun main() {
    val playerNames: List<String> = ConsoleInput.inputPlayerNames()
    val players: List<Player> = playerNames.map { Player(it) }
    val game = Game(players)
    ConsoleOutput.printPlayersCards(game.players.map { PlayerCardDto.from(it) })
    draw(game)
    ConsoleOutput.printPlayersResult(players.map { PlayerResultDto.from(it) })
}

private fun draw(game: Game) {
    game.players.forEach {
        while (ConsoleInput.inputPlayerDraw(it.name) == "y") {
            game.drawTo(it)
            ConsoleOutput.printPlayerCards(PlayerCardDto.from(it))
        }
    }
}
