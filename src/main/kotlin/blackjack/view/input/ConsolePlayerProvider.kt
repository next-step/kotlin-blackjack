package blackjack.view.input

import blackjack.model.player.Player
import blackjack.model.player.PlayerProvider
import blackjack.model.player.Players
import blackjack.model.player.Players.Companion.toPlayers
import blackjack.view.input.parser.PlayerNamesInputParser

class ConsolePlayerProvider : PlayerProvider {

    private val playNamesInputParser = PlayerNamesInputParser()
    private val hitDecisionMaker = ConsoleHitDecisionMaker()

    override fun createPlayers(): Players =
        ConsoleReader.read(MESSAGE_INPUT_PLAYERS, playNamesInputParser)
            .map { name -> Player(name, hitDecisionMaker) }
            .toPlayers()

    companion object {
        private const val MESSAGE_INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
