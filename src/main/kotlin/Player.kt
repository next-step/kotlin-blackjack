class Player(val name: String, val hand: Hand) {
    fun drawCard(cards: List<PlayingCard>) {
        hand.add(cards)
    }
}
