package domain.turn

import domain.card.CardDeck
import domain.gamer.Gamer
import domain.gamer.Gamers
import domain.gamer.player.Player

object IntermediateTurn : Turn {

    override fun proceed(
        gamers: Gamers,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit,
        askPlayerWantToStay: ((String) -> Boolean)?,
    ) {
        gamers.gamerToHit()?.let {
            if (it is Player && askPlayerWantToStay?.invoke(it.name) == true) {
                proceedStay(it, changeState)
                return
            }
            proceedHit(it, cardDeck, changeState)
        } ?: run {
            changeState(FinalTurn)
        }
    }

    private fun proceedHit(it: Gamer, cardDeck: CardDeck, changeState: (Turn) -> Unit) {
        it.hit(cardDeck)
        changeState(IntermediateTurn)
    }

    private fun proceedStay(it: Player, changeState: (Turn) -> Unit) {
        it.stay()
        changeState(IntermediateTurn)
    }
}
