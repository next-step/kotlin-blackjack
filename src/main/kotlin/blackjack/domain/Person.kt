package blackjack.domain

class Person(val name: String, val ownCards: OwnCards = OwnCards(), private var state: State = State.CONTINUE) {
    fun addCard(drawYn: Boolean) {
        ownCards.addCard(drawYn)

        if (ownCards.sumCardNumber() > Draw.DRAW_LIMIT) state = State.LOSE
        else if (ownCards.sumCardNumber() == Draw.DRAW_LIMIT) state = State.WIN
    }

    fun checkDrawable(drawYn: Boolean): Boolean = state == State.CONTINUE && drawYn
}
