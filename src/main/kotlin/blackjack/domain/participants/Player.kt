package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.card.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Player(
    name: String,
    initCards: ArrayList<Card> = arrayListOf<Card>()
) : Participant(name, initCards){

    fun isWinner(dealer: Dealer): Boolean {
        val dealerScore = dealer.getScore()

        if(state is Blackjack) return true
        if(this.state is Bust) return false
        if(dealerScore > 21) return true
        if(this.getScore() > dealerScore) return true
        return false
    }

    override fun checkCardDrawAvailable(): Boolean {
        return !state.isFinished
    }
}