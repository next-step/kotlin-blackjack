package blackjack.domain

data class Card(val suite: Suite, val denomination: Denomination) {
    companion object {
        val DECK = Suite.values()
            .flatMap { suite ->
                Denomination.values()
                    .map {
                        Card(suite, it)
                    }
            }
    }
}
