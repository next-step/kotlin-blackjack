package model

class Cards(val list: List<Card>) {
    private var index: Int = 0

    constructor() : this(CARD_LIST.list)

    fun shuffle(): Cards {
        return Cards(list.shuffled())
    }

    fun pick(): Card {
        val card = list[index]
        index++
        return card
    }

    companion object {
        val CARD_LIST = Cards(Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } })
    }
}
