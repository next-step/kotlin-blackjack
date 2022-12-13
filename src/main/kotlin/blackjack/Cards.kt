package blackjack

class Cards {
    private val takeCards = ArrayList<Card>()

    fun getTakeCards(): List<Card> = takeCards

    fun takeCard(card: Card) {
        takeCards.add(card)
    }

    fun sum(): Int {
        val sum = takeCards.sumOf { it.number.value }
        return sumChangeAce(sum)
    }

    private fun sumChangeAce(sum: Int): Int {
        var changeSum = sum
        val aceCount = takeCards.count { it.number is CardNumber.Ace }
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
