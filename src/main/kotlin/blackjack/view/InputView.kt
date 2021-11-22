package blackjack.view

import blackjack.model.Bet
import blackjack.model.Name
import blackjack.model.Names
import blackjack.model.Player
import blackjack.util.readInt
import blackjack.view.res.Resource

class InputView {

    fun getPlayers(): List<Player> = getNames()
        ?.toList()
        ?.map { name ->
            println(Resource.STR_ASK_BET.format(name))
            val amount = readInt()
            Player.ready(name, Bet.valueOf(amount))
        }
        ?: emptyList()

    private fun getNames(): Names? {
        println(Resource.STR_ASK_NAMES)

        val input = readLine() ?: return null
        return input.split(NAME_DELIMITER)
            .map { Name.valueOf(it.trim()) }
            .let(Names::from)
    }

    fun askDraw(player: Player): DrawAction {
        println(Resource.STR_ASK_DRAW.format(player.name))
        return readLine()
            ?.let { symbol ->
                when (symbol.uppercase()) {
                    ASK_DRAW_YES -> DrawAction.YES
                    ASK_DRAW_NO -> DrawAction.NO
                    else -> DrawAction.NO
                }
            }
            ?: DrawAction.NO
    }

    companion object {
        private const val ASK_DRAW_YES = "Y"
        private const val ASK_DRAW_NO = "N"

        private const val NAME_DELIMITER = ","
    }
}
