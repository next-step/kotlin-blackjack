package blackjack

class CardsCompound private constructor(private val set: Set<Int>) {
    fun addNumber(number: CardNumber): CardsCompound {
        return CardsCompound(
            HashSet<Int>().apply {
                for (score in number.getScore().number) {
                    setNewCompound(set.toList(), score)
                }
            }
        )
    }

    private fun HashSet<Int>.setNewCompound(entries: List<Int>, score: Int) {
        for (entry in entries) {
            val cardSum = entry + score
            if (cardSum <= BEST) add(cardSum)
        }
    }

    val bestNumber get() = set.toList().maxOrNull() ?: BUSTED
    val isBusted get() = set.isEmpty()

    companion object {
        const val BEST = 21
        const val BUSTED = 22

        fun get(): CardsCompound = CardsCompound(hashSetOf(0))
    }
}
