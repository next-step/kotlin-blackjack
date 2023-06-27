package blackjack.domain

class BlackJackRule {

    fun point(cards: PlayerCards): Int {
        val totalPoint = cards.sumOf { it.value.point }
        val aceCount = cards.count { it.value == CardValue.ACE }
        return List(aceCount) { ACE_OPTIONAL_POINT }.fold(totalPoint) { acc, optionalPoint ->
            if (acc + optionalPoint <= WIN_POINT) return acc + optionalPoint else acc
        }
    }

    val winningPoint: Int = WIN_POINT

    companion object {
        private const val WIN_POINT = 21
        private const val ACE_OPTIONAL_POINT = 10
    }
}
