package balckjack

sealed interface Card {
    val pattern: CardPattern

    fun score(): Score
}

enum class CardPattern {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER
}
