package model

class Card(val cardNumber: CardNumber, val cardShape: CardShape) {
    companion object {
        private val cards = mutableListOf<Card>()
        private fun selectCardNumber(): CardNumber {
            return CardNumber.values().toList().shuffled()[0]
        }

        private fun selectCardShape(): CardShape {
            return CardShape.values().toList().shuffled()[0]
        }

        fun generateCard(): Card {
            var number = selectCardNumber()
            var shape = selectCardShape()
            while (isDuplicate(number, shape)) {
                number = selectCardNumber()
                shape = selectCardShape()
            }
            return Card(number, shape)
        }

        private fun isDuplicate(cardNumber: CardNumber, cardShape: CardShape): Boolean {
            val card = cards.count { it.cardNumber == cardNumber && it.cardShape == cardShape }
            return card != 0
        }
    }
}
