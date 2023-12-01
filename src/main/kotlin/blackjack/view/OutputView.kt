package blackjack.view

import blackjack.domain.Game
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit

object OutputView {
    fun printGameInitialization(game: Game) {
        println("${game.players.values.joinToString(separator = ", ") { it.name }}에게 2장씩 나누었습니다.")
        game.players.values.forEach {
            printPlayerAndCard(it)
        }
    }

    fun printPlayerAndCard(player: Player) {
        println("${player.name}카드: ${player.hand.makeForm()}")
    }

    fun printPlayerAndCardAndScore(player: Player) {
        println("${player.name}카드: ${player.hand.makeForm()} - 결과: ${player.resultScore.value}")
    }

    private fun Hand.makeForm() =
        this.cards.joinToString(separator = ", ") { "${it.rank.makeForm()}${it.suit.makeForm()}" }

    private fun Rank.makeForm() = when (this) {
        Rank.ACE -> "A"
        Rank.TWO -> "2"
        Rank.THREE -> "3"
        Rank.FOUR -> "4"
        Rank.FIVE -> "5"
        Rank.SIX -> "6"
        Rank.SEVEN -> "7"
        Rank.EIGHT -> "8"
        Rank.NINE -> "9"
        Rank.TEN -> "10"
        Rank.JACK -> "J"
        Rank.QUEEN -> "Q"
        Rank.KING -> "K"
    }

    private fun Suit.makeForm() = when (this) {
        Suit.CLUB -> "클로버"
        Suit.DIAMOND -> "다이아몬드"
        Suit.HEART -> "하트"
        Suit.SPADE -> "스페이드"
    }
}
