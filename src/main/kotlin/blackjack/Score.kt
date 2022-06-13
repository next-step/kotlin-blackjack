package blackjack

class Score(val cards: List<Card>) {

    fun getScore(): Int {
        var sumOf = cards.sumOf { it.cardSymbol.score }
        val aceCount = cards.count { it.cardSymbol == CardSymbol.ACE }

        repeat(aceCount) {
            if (sumOf <= BLACKJACK) {
                return sumOf
            }
            sumOf -= CORRECTION_NUMBER
        }

        return sumOf
    }

    companion object {
        private const val CORRECTION_NUMBER = 10
        private const val BLACKJACK = 21
    }
}
