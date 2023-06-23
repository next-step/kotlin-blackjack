package blackjack

class Rule {
    companion object {
        fun calculateSum(cards: List<Card>): Int {
            var sum = 0
            cards.filterNot { it.value == CardValue.ACE }.forEach {
                sum += it.value.toInt(sum)
            }
            cards.filter { it.value == CardValue.ACE }.forEach {
                sum += it.value.toInt(sum)
            }
            return sum
        }

        private fun CardValue.toInt(sum: Int): Int {
            return when (this) {
                CardValue.TWO -> 2
                CardValue.THREE -> 3
                CardValue.FOUR -> 4
                CardValue.FIVE -> 5
                CardValue.SIX -> 6
                CardValue.SEVEN -> 7
                CardValue.EIGHT -> 8
                CardValue.NINE -> 9
                CardValue.ACE -> if (sum + 11 > 21) 1 else 11
                else -> 10
            }
        }
    }
}
