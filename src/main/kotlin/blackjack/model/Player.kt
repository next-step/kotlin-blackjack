package blackjack.model

class Player(val name: String) {
    var cards = Cards.Builder().build()

    fun draw() {
        val mutableCardList = cards.toMutableList()
        mutableCardList.add(Card.get())

        cards = Cards.Builder().cards(
            mutableCardList
        ).build()
    }
}
