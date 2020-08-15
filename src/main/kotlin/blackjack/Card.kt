package blackjack

data class Card(
    private val shape: Symbol,
    private val number: Numbers
) {
    fun getCardScore(): Int {
        return number.score
    }

    override fun toString(): String {
        return "${number.shape}${shape.symbol}"
    }

    companion object {
        fun getInstances(): Card {
            return Card(getShape(), getNumber())
        }

        private fun getShape(): Symbol {
            return Symbol.values().map { it }.shuffled()[0]
        }

        private fun getNumber(): Numbers {
            return Numbers.values().map { it }.shuffled()[0]
        }
    }
}
