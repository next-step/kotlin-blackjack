package model

class Dealer {
    companion object {
        val CARD_LIST: List<Card>
            get() {
                val cardList = mutableListOf<Card>()
                Suit.values().map { suit -> { Denomination.values().map { denomination -> { Card(suit, denomination) } } } }
                return cardList.toList()
            }
    }
}