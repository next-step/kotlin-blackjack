package blackjack

class Player(val name: String, private val cards: MutableList<PlayingCard> = mutableListOf()) {

    fun receive(card: PlayingCard) {
        cards.add(card)
    }

    fun cards(): List<PlayingCard> = cards.toList()
}
