package model

class Player(val name: PlayerName) {
    private val cardMutableList: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = cardMutableList.toList()

    fun receive(card: Card) {
        cardMutableList.add(card)
    }
}
