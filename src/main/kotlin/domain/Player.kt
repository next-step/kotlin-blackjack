package domain

class Player {

    val hands: Cards = Cards()

    val handsCardCount: Int
        get() = hands.count

    fun receiveCard(card: Card) {
        this.hands.add(card)
    }

    fun handsCardScore(): Int = this.hands.score()
    fun handsCardCountWithAceHighScore(): Int = this.hands.secondaryScore()
}