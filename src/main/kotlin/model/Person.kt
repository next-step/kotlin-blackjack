package model

open class Person {
    private val cards: MutableList<Card> = mutableListOf()
    protected var name: String = ""

    fun receiveCard(card: Card): Boolean {
        return cards.add(card)
    }

    fun openCard(): List<Card> {
        return cards
    }

    fun notifyName(): String {
        return name
    }
}
