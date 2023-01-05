package model

class Players {
    private val players = mutableMapOf<String, MutableList<Card>>()

    private fun add(name: String, card: MutableList<Card>) {
        players[name] = card
    }

    fun updateCard(name: String, card: Card) {
        players[name]?.add(card)
    }

    fun get(): Map<String, List<Card>> {
        return players
    }

    fun generate(names: List<String>) {
        names.forEach {
            add(it, mutableListOf())
        }
    }
}
