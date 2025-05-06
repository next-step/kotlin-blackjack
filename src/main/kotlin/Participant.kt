interface Participant {
    val hand: Hand

    fun drawCards(cards: List<PlayingCard>)
}
