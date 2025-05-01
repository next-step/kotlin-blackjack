class Player(val name: String, val hand: Hand) {
    fun drawCard(card: PlayingCard) {
        hand.add(card)
    }
}
