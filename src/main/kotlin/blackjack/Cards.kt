package blackjack

class Cards(val card1: String, val card2: String) {

    fun sum(): Int {
        return cardValue(card1) + cardValue(card2)
    }

    private fun cardValue(card: String): Int {
        return if (card.equals("K")) 10 else card.toInt()
    }
}
