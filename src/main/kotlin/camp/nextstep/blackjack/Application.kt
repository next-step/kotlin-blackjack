package camp.nextstep.blackjack

import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.ui.cli.GameInitializedInfoWriter
import camp.nextstep.blackjack.ui.cli.GameResultWriter
import camp.nextstep.blackjack.ui.cli.PlayerActionReader
import camp.nextstep.blackjack.ui.cli.PlayerCardsWriter
import camp.nextstep.blackjack.ui.cli.PlayersReader

fun main() {

    val players = PlayersReader.read()
    val game = BlackJackGame.new(players)

    GameInitializedInfoWriter.write(game.participants)

    for (player in players) {
        PlayerCardsWriter.write(player)
    }

    val turns = game.turns
    for (turn in turns) {
        game.doTurn(turn) { PlayerActionReader.read(turn.player) }
    }

    val result = game.result()
    GameResultWriter.write(result)
}
