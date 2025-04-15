class Hand(private val cards: List<PlayingCard>) {
    fun score(): Int {
        return cards.sumOf { it.denomination.score }
    }
}
