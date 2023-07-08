package domain.turn

import domain.card.CardDeck
import domain.gamer.Gamers

object IntermediateTurn : Turn {

    override fun proceed(
        gamers: Gamers,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    ) {
        gamers.gamerToHit()?.let {
            it.hit(cardDeck)
            changeState(IntermediateTurn)
        } ?: run {
            changeState(FinalTurn)
        }
    }
}
