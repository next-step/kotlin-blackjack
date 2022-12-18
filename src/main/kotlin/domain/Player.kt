package domain

class Player(
    val name: String
) {

    val hands: Cards = Cards()

    val handsCardCount: Int
        get() = hands.count

    fun receiveCard(card: Card) {
        this.hands.add(card)
    }

    fun handsCardScore(): Int = this.hands.score()
    fun handsCardCountWithAceHighScore(): Int = this.hands.secondaryScore()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
