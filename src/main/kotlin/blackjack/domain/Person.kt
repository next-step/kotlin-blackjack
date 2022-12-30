package blackjack.domain

class Person(val name: String, val ownCards: OwnCards = OwnCards(Draw.FIRST_DRAW_COUNT), private var state: State = State.CONTINUE) {
    fun addCard(drawYn: Boolean) {
        if (drawYn) {
            ownCards.addCard(Card())
            state =
                if (ownCards.sumCardNumber() > Draw.DRAW_LIMIT) State.LOSE
                else if (ownCards.sumCardNumber() == Draw.DRAW_LIMIT) State.WIN
                else State.CONTINUE
        }
    }

    fun checkDrawable(drawYn: Boolean): Boolean = (state == State.CONTINUE) && drawYn
}
