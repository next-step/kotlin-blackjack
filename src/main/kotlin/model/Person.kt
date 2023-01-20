package model

open class Person {
    private val cards: MutableList<Card> = mutableListOf()
    private var result: String = ""
    protected var name: String = ""

    fun receiveCard(card: Card): Boolean {
        return cards.add(card)
    }

    fun openCard(): List<Card> {
        return cards
    }

    fun notifyResult(): String {
        return result
    }

    fun updateResult(result: String) {
        this.result = result
    }

    fun notifyName(): String {
        return name
    }
}
