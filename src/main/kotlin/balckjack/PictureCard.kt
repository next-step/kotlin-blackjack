package balckjack

sealed interface PictureCard : Card

data class King(override val pattern: CardPattern) : PictureCard {
    override fun score(): Score {
        return SingleScore(10)
    }
}

data class Queen(override val pattern: CardPattern) : PictureCard {
    override fun score(): Score {
        return SingleScore(10)
    }
}

data class Jack(override val pattern: CardPattern) : PictureCard {
    override fun score(): Score {
        return SingleScore(10)
    }
}

data class Ace(override val pattern: CardPattern) : PictureCard {
    override fun score(): Score {
        return DoubleScore(minNumber = 1, maxNumber = 11)
    }
}
