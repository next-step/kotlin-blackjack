package blackjack.view

import blackjack.dto.GameResult
import blackjack.entity.Game

object OutputView {


    private fun createCardInfos(game: Game): String {
        val cards = game.getPlayerBlackJack().card
        return cards.flatMap { cardMap ->
            cardMap.map { (symbol, card) ->
                String.format("%s%s", card.getValue(), symbol.getType())
            }
        }.joinToString(", ")
    }

    fun results(results: GameResult) {
        println("## 최종 승패")
        results.playerResults.forEach { playerResult -> println(playerResult.run { getResult() }) }
    }
}
