package blackjack.ui

import blackjack.domain.card.Cards
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.score.GameResult

object OutputView {
    private const val NAME_SEPARATOR = ", "

    fun printInitState(players: Players, initDrawSize: Int) {
        val playerNames = players.getNames()
        println("${playerNames.joinToString(NAME_SEPARATOR)} 에게 ${initDrawSize}장의 나누었습니다.")
        players.players.forEach(::printPlayersCard)
    }

    fun printPlayersCard(player: Player) {
        println("${player.name}카드: ${getCardsName(player.cards)}")
    }

    private fun getCardsName(cards: Cards): String =
        cards.cards.joinToString(NAME_SEPARATOR) { "${OutViewConstMap[it.cardNumber]}${OutViewConstMap[it.cardSymbol]}" }

    fun printResults(scoreResults: List<GameResult>) {
        scoreResults.forEach { (player, score) ->
            println("${player.name}카드: ${getCardsName(player.cards)} - 결과: ${score.value}")
        }
    }
}
