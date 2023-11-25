package blackjack

class CardsCompound private constructor(private val set: Set<Int>) {
    private var bustedNumber: Int = 0
    fun addNumber(number: CardNumber): CardsCompound {
        val newCompound = CardsCompound(
            HashSet<Int>().apply {
                for (score in number.getScore().number) {
                    setNewCompound(set.toList(), score)
                }
            }
        )

        if (newCompound.set.isEmpty()) {
            newCompound.setBustedNumber(set.min(), number.getScore().number.min())
        }

        return newCompound
    }

    private fun HashSet<Int>.setNewCompound(entries: List<Int>, score: Int) {
        for (entry in entries) {
            val cardSum = entry + score
            if (cardSum <= BEST) {
                add(cardSum)
            }
        }
    }

    private fun setBustedNumber(minSetNum: Int, minCardNumber: Int) {
        bustedNumber = minSetNum + minCardNumber
    }

    val bestNumber get() = set.maxOrNull() ?: bustedNumber

    companion object {
        const val BEST = 21

        fun get(): CardsCompound = CardsCompound(hashSetOf(0))
    }
}
