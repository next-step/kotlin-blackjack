package model

data class Card(val cardNumber: CardNumber, val cardShape: CardShape) {
    companion object {
        private val cards = mutableListOf<Card>()
        private fun selectCardNumber(): CardNumber {
            return CardNumber.values().toList().shuffled()[0]
        }

        private fun selectCardShape(): CardShape {
            return CardShape.values().toList().shuffled()[0]
        }

        fun generate(): Card {
            var number = selectCardNumber()
            var shape = selectCardShape()
            while (isDuplicate(number, shape)) {
                number = selectCardNumber()
                shape = selectCardShape()
            }
            val card = Card(number, shape)
            cards.add(card)
            return card
        }

        private fun isDuplicate(cardNumber: CardNumber, cardShape: CardShape): Boolean {
            val duplicatedCards = cards.count { it.cardNumber == cardNumber && it.cardShape == cardShape }
            return duplicatedCards != 0
        }
    }
}
