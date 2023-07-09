package domain.turn

import domain.card.CardDeck
import domain.gamer.Gamers

interface Turn {
    fun proceed(
        gamers: Gamers,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit,
        askPlayerWantToStay: ((String) -> Boolean)? = null,
    )
}
