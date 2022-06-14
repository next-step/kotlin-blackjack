package blackjack.view.input

import blackjack.model.player.Player
import blackjack.model.player.PlayerProvider
import blackjack.model.player.Players
import blackjack.model.player.Players.Companion.toPlayers
import blackjack.view.input.parser.IntInputParser
import blackjack.view.input.parser.PlayerNamesInputParser

class ConsolePlayerProvider : PlayerProvider {

    private val playNamesInputParser = PlayerNamesInputParser()
    private val hitDecisionMaker = ConsoleHitDecisionMaker()
    private val betMoneyInputParser = IntInputParser(minValue = Player.MIN_BET_MONEY)

    override fun createGuestPlayers(previousPlayers: Players<Player.Guest>?): Players<Player.Guest> {
        val playerNames = previousPlayers?.map { it.name }
            ?: ConsoleReader.read(MESSAGE_INPUT_PLAYERS, playNamesInputParser)

        return playerNames.map { name ->
            Player.Guest(name = name, hitDecisionMaker = hitDecisionMaker, betMoney = readBetMoneyFor(name))
        }.toPlayers()
    }

    private fun readBetMoneyFor(name: String): Int =
        ConsoleReader.read("${name}의 배팅 금액은?", betMoneyInputParser)

    companion object {
        private const val MESSAGE_INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
