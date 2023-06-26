package blackjack.domain

data class Card(val cardImage: CardImage, val cardLevel: CardLevel) {
    companion object {
        fun newCard(block: CardBuilder.() -> Unit): Card {
            return CardBuilder().apply(block).build()
        }
    }
}

enum class CardImage {
    HEART, SPADE, CLOVER, DIAMOND
}

enum class CardLevel {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    KING,
    QUEEN
}

@JvmInline
value class CardValue(val value: Int) {
    companion object {
        fun Int.cardValue(): CardValue = CardValue(this)
    }
}

class CardBuilder {

    private var cardImage: CardImage? = null
    private var cardLevel: CardLevel? = null

    infix fun CardImage.with(cardLevel: CardLevel) {
        this@CardBuilder.cardImage = this
        this@CardBuilder.cardLevel = cardLevel
    }

    fun build(): Card {
        require(cardImage != null)
        require(cardLevel != null)

        return Card(cardImage!!, cardLevel!!)
    }
}
