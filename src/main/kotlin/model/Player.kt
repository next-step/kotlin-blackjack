package model

class Player(name: String) {
    private var name = ""
    private val cards = mutableListOf<Card>()

    init {
        this.name = name
    }

    fun addCard(card: Card): Boolean {
        return cards.add(card)
    }

    fun getCard(): List<Card> {
        return cards
    }

    fun getName(): String {
        return name
    }
}
