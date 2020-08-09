package blackjack.model

class Deck(private val type: Card.Type, private val shape: Card.Shape) {

    override fun toString(): String = "${type.nickName}${shape.nickName}"

    fun getDeckType(): Card.Type = Card.Type.findByNickname(type.nickName)

    companion object {
        const val TOTAL_DECK_SIZE = 56
        private val all = mutableListOf<Deck>().apply {
            for (type in Card.Type.values()) {
                for (shape in Card.Shape.values()) {
                    add(Deck(type, shape))
                }
            }
        }

        fun pop(): Deck = all
            .shuffled()
            .first()
            .also { all.remove(it) }
    }
}
