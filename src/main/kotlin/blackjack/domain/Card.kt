package blackjack.domain

data class Card(val suitType: SuitType, val numberType: NumberType) {

    companion object {
        private val ALL_CARDS = NumberType
            .values()
            .flatMap { cardType ->
                SuitType
                    .values()
                    .map { suitType ->
                        suitType to cardType
                    }
            }
            .associateWith { Card(it.first, it.second) }

        val CARD_LIST = ALL_CARDS.values.toList()
    }
}
