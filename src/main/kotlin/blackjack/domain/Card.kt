package blackjack.domain

data class Card(val suitType: SuitType, val numberType: NumberType) {

    companion object {
        private val ALL_CARDS = NumberType
            .values()
            .flatMap(::generateSuitNumberPair)
            .associateWith { Card(it.first, it.second) }

        private fun generateSuitNumberPair(numberType: NumberType): List<Pair<SuitType, NumberType>> = SuitType
            .values()
            .map { suitType ->
                suitType to numberType
            }

        val CARD_SET = ALL_CARDS.values.toSet()
    }
}
