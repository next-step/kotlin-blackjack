package blackjack.ui

import blackjack.domain.card.Cards
import blackjack.domain.score.GameResult
import blackjack.ui.model.PlayerOutputModel

object OutputView {
    private const val NAME_SEPARATOR = ", "

    fun printInitState(players: List<PlayerOutputModel>, initDrawSize: Int) {
        val playerNames = players.map { it.name }
        println("${playerNames.joinToString(NAME_SEPARATOR)} 에게 ${initDrawSize}장의 나누었습니다.")
        players.forEach(::printPlayersCard)
    }

    fun printPlayersCard(player: PlayerOutputModel) {
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
