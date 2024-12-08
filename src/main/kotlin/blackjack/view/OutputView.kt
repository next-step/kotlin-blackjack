package blackjack.view

import blackjack.entity.Game

object OutputView {
    fun results(games: List<Game>) {
        games.forEach { game ->
            game.player
            println("${game.player}카드: ${createCardInfos(game)} - 결과: ${game.getPlayerBlackJack().getTotalCardValue()}")
        }
    }

    private fun createCardInfos(game: Game): String {
        val cards = game.getPlayerBlackJack().card
        return cards.flatMap { cardMap ->
            cardMap.map { (symbol, card) ->
                String.format("%s%s", card.getValue(), symbol.getType())
            }
        }.joinToString(", ")
    }
}
