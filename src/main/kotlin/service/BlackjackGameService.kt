package service

import domain.CardDeck
import domain.Dealer
import domain.Participant
import domain.Players

private const val INITIAL_DRAW_CARD_SIZE = 2

class BlackjackGameService {
    private val deck = CardDeck()

    fun drawInitialCards(
        dealer: Dealer,
        players: Players,
    ) {
        repeat(INITIAL_DRAW_CARD_SIZE) {
            dealer.cards.addCard(deck.drawCard())
            players.players.forEach { player -> player.cards.addCard(deck.drawCard()) }
        }
    }

    fun drawCards(participant: Participant) {
        participant.cards.addCard(deck.drawCard())
    }
}
