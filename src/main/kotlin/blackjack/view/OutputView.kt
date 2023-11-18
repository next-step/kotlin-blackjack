package blackjack.view

import blackjack.model.CardDeck
import blackjack.model.GameResult
import blackjack.model.Player

object OutputView {
    fun renderPlayers(players: List<Player>) {
        players.forEach {
            renderPlayer(it, ::println)
        }
    }

    fun renderPlayer(player: Player, printer: (Message: Any) -> Unit) {
        printer(
            "${player.name}카드: ${
                getCardString(player.cardDeck)
            }"
        )
    }

    fun renderResult(results: List<GameResult>) {
        results.forEach {
            renderPlayer(it.player, ::print)
            println("- 결과: ${it.resultCount}")
        }
    }

    private fun getCardString(cardDeck: CardDeck): String = cardDeck.cards.joinToString(", ") {
        it.name
    }
}
