package domain

class Player(val name: String) {
    private val holdingCard = mutableListOf<Card>()

    val cards
        get() = holdingCard.toList()

    fun offer(card: Card) {
        if(holdingCard.contains(card)) {
            throw IllegalArgumentException()
        }

        holdingCard.add(card)
    }

    fun cardSum(): Int {
        return holdingCard.sumOf { it.score.point }
    }
}