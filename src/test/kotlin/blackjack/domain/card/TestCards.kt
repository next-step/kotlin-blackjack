package blackjack.domain.card

object TestCards {
    fun getSeventeenPointCards(): Cards {
        return Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SEVEN),
            )
        )
    }

    fun getSixteenPointCards(): Cards {
        return Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SIX),
            )
        )
    }

    fun getTwentyPointCards(): Cards {
        return Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
    }

    fun getBurstCards(): Cards {
        return Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.NINE),
                Card(Shape.CLOVER, Character.FIVE)
            )
        )
    }

    fun getBlackJack(): Cards {
        return Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.K),
                Card(Shape.CLOVER, Character.A)
            )
        )
    }
}