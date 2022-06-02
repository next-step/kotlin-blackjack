package balckjack

sealed interface PictureCard : Card

data class King(override val pattern: CardPattern) : PictureCard {
    override fun count(): CardCount {
        return SingleCount(10)
    }
}

data class Queen(override val pattern: CardPattern) : PictureCard {
    override fun count(): CardCount {
        return SingleCount(10)
    }
}

data class Jack(override val pattern: CardPattern) : PictureCard {
    override fun count(): CardCount {
        return SingleCount(10)
    }
}

data class Ace(override val pattern: CardPattern) : PictureCard {
    override fun count(): CardCount {
        return DoubleCount(minNumber = 1, maxNumber = 11)
    }
}
