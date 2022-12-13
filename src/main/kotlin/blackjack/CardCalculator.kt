package blackjack

class CardCalculator(private val cards: List<Card>) {
    fun sum(): Int {
        val sum = cards.sumOf { it.number.value }
        return sumChangeAce(sum)
    }

    private fun sumChangeAce(sum: Int): Int {
        var changeSum = sum
        val aceCount = cards.count { it.number is CardNumber.Ace }
        val aceDiffValue = CardNumber.Ace.diffValue()

        repeat(aceCount) {
            if (changeSum + aceDiffValue < STD_NUMBER)
                changeSum += aceDiffValue
        }
        return changeSum
    }

    companion object {
        const val STD_NUMBER = 21
    }
}
