package domain

class Player(val name: String) {
    private val holdingCard = mutableListOf<Card>()
    val cards
        get() = holdingCard.toList()

    fun offer(cards: List<Card>) {
        cards.forEach {
            if (holdingCard.contains(it)) {
                throw IllegalArgumentException()
            }

            holdingCard.add(it)
        }
    }

    fun cardSum(): Int {
        return holdingCard.sumOf { it.score.point }
    }
}
