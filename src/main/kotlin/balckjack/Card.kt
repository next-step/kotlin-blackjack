package balckjack

sealed interface Card {
    val pattern: CardPattern

    fun count(): CardCount
}

enum class CardPattern {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER
}
