package blackjack

class Cards(val card1: String, val card2: String) {

    fun sum(): Int {
        return card1.toInt() + card2.toInt()
    }
}
