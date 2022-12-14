package blackjack.domain

data class Card(val suite: Suite, val denomination: Denomination) {
    override fun toString(): String {
        return "${denomination.value}${suite.value}"
    }
    companion object {
        val ALL_CARDS = Suite.values()
            .flatMap { suite ->
                Denomination.values()
                    .map {
                        Card(suite, it)
                    }
            }.shuffled()
    }
}
