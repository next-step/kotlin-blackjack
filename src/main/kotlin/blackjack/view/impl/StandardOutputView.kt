package blackjack.view.impl

import blackjack.model.Cards
import blackjack.model.Dealer
import blackjack.model.DualResult
import blackjack.model.Gambler
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.KoreanMessageSource
import blackjack.view.OutputView

class StandardOutputView(
    private val messageSource: KoreanMessageSource = KoreanMessageSource()
) : OutputView {
    override val printInitCards: (Dealer, Players) -> Unit = { dealer, players ->
        val playerNames = players.joinToString(SEPARATE_SYMBOL) { it.name }

        println()
        println("딜러와 ${playerNames}에게 각각 2장의 카드를 나누었습니다.")

        println("딜러카드: ${cardsDetailOf(dealer.cards)}")
        players.forEach {
            println("${it.name}카드: ${cardsDetailOf(it.cards)}")
        }
        println()
    }

    override val printPlayerCards: (Player) -> Unit = { player ->
        println("${player.name}카드: ${cardsDetailOf(player.cards)}")
    }

    override val printCardResult: (Dealer, Players) -> Unit = { dealer, players ->
        val message: (String, Gambler) -> String = { name, player ->
            "${name}카드: ${cardsDetailOf(player.cards)} - 결과: ${player.getFinalScore()}"
        }

        println()
        println(message("딜러", dealer))
        players.forEach {
            println(message(it.name, it))
        }
    }

    override val printDealerDraw: (Dealer) -> Unit = {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private val cardsDetailOf: (Cards) -> String = { cards ->
        cards.joinToString(SEPARATE_SYMBOL) { messageSource.nameOf(it) }
    }

    override val printGameResult: (Players, GameResult) -> Unit = { players, gameResult ->
        println()
        println("딜러: ${getDealerResultMessage(gameResult)}")
        println(getPlayersResultMessage(players, gameResult))
    }

    private fun getDealerResultMessage(gameResult: GameResult): String {
        return DualResult.values()
            .map { it to gameResult.getDealerResultCountOf(it) }
            .filterNot { it.second == null }
            .joinToString(" ") { "${it.second}${messageSource.nameOf(it.first)}" }
    }

    private fun getPlayersResultMessage(players: Players, gameResult: GameResult): String {
        return players.joinToString("\n") {
            "${it.name}: ${messageSource.nameOf(gameResult.getPlayerDualResultOf(it))}"
        }
    }

    companion object {
        private const val SEPARATE_SYMBOL = ","
    }
}
