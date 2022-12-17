package domain

class CardDeck {

    val cards: List<Card>

    init {
        val mutableCardList = mutableListOf<Card>()
        CardShape.values().forEach { shape ->
            CardNumber.values().forEach { number ->
                val card = Card(shape = shape, number = number)
                mutableCardList.add(card)
            }
        }
        cards = mutableCardList.toList()
    }
}