package blackjack

data class Card(val type: CardType, val cardNumber: CardNumber) {

    companion object {
        const val BLACK_JACK_NUM = 21
    }
}
