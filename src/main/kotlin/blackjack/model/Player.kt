package blackjack.model

class Player(private val name: String) {
    var cards = Cards()

    override fun toString(): String {
        return name
    }

    fun draw() {
        val mutableCardList = cards.toMutableList()
        mutableCardList.add(Card.get())

        cards = Cards(mutableCardList)
    }
}
