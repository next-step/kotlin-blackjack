class Player(val name: String, override val hand: Hand) : Participant {
    override fun drawCards(cards: List<PlayingCard>) {
        hand.add(cards)
    }
}
