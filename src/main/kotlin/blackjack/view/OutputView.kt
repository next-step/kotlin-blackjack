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
        val blackJack = game.getPlayerBlackJack()
        return blackJack.card.map { (CardSymbol, Card) -> String.format("%s%s", Card.getValue(), CardSymbol.getTye()) }.joinToString(", ")
    }
}
