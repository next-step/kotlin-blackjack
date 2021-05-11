package blackjack.view

import blackjack.domain.Game
import blackjack.domain.Cards

object ResultView {
    private const val NAME_SEPARATOR = ","

    fun printAllPlayerCards(game: Game) {
        game.players.forEach {
            printPlayerCards(it.name, it.cards)
        }
    }

    fun printAllResult(game: Game) {
        game.players.forEach {
            printResult(it.name, it.cards)
        }
    }

    fun printInitNotice(names: List<String>, blackJackCardCount: Int) {
        println("${names.joinToString(NAME_SEPARATOR)} 에게 $blackJackCardCount 장을 나누었습니다. ")
    }

    fun printPlayerCards(name: String, cards: Cards) {
        println("${name}카드: ${explainCards(cards)}")
    }

    private fun printResult(name: String, cards: Cards) {
        println("${name}카드: ${explainCards(cards)} - 결과 : ${cards.score}")
    }

    private fun explainCards(cards: Cards): String {
        return cards.joinToString(NAME_SEPARATOR) { "${it.number.displayName}${it.suite.koreanName}" }
    }
}
