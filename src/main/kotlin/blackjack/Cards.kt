package blackjack

class Cards(val card1: String, val card2: String) {

    fun sum(): Int {
        return cardValue(card1) + cardValue(card2)
    }

    private fun cardValue(card: String): Int {
        return if (isCharacterCard(card)) 10 else card.toInt()
    }

    private fun isCharacterCard(card: String): Boolean {
        return card.equals("K") || card.equals("J")
    }
}
