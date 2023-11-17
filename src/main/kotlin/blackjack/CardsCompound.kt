package blackjack

class CardsCompound private constructor(val set: HashSet<Int>) {
    fun addNumber(number: CardNumber) {
        val entries = set.toList()
        set.clear()
        for (score in number.getScore().number) {
            setNewCompound(entries, score)
        }
    }

    private fun setNewCompound(entries: List<Int>, score: Int) {
        for (entry in entries) {
            val cardSum = entry + score
            if (cardSum <= BEST) set.add(cardSum)
        }
    }

    val bestNumber get() = set.toList().maxOrNull() ?: BUSTED
    val isBusted get() = set.isEmpty()

    companion object {
        const val BEST = 21
        const val BUSTED = -1

        fun get(): CardsCompound = CardsCompound(hashSetOf(0))
    }
}
