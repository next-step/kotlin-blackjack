package blackjack

class Score(val cards: List<Card>) {

    fun getScore(): Int {

        val reversed = cards.sortedBy { it.cardSymbol.score }
            .reversed()
        var sum = 0
        reversed.forEach {
            sum += it.cardSymbol.score
            if (it.cardSymbol == CardSymbol.ACE) {
                if (sum + CORRECTION_NUMBER <= BLACKJACK) {
                    sum += CORRECTION_NUMBER
                }
            }
        }

        var sumOf = cards.sumOf { it.cardSymbol.score }
        val aceCount = cards.count { it.cardSymbol == CardSymbol.ACE }

        repeat(aceCount) {
            if (sumOf + CORRECTION_NUMBER <= BLACKJACK) {
                sumOf += CORRECTION_NUMBER
            }
        }

        return sumOf
    }

    companion object {
        private const val CORRECTION_NUMBER = 10
        private const val BLACKJACK = 21
    }
}
