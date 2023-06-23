package blackjack.domain

class Card(val shape: CardShape, val number: CardNumber) {

    companion object {
        const val TOTAL_NUMBER_OF_CARDS = 52
    }
}