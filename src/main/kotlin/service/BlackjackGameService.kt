package service

import domain.CardDeck
import domain.Dealer
import domain.Player
import domain.Players

private const val INITIAL_DRAW_CARD_SIZE = 2

class BlackjackGameService {
    private val deck = CardDeck()

    fun drawInitialCards(
        dealer: Dealer,
        players: Players,
        printCard: () -> Unit,
    ) {
        repeat(INITIAL_DRAW_CARD_SIZE) {
            dealer.cards.addCard(deck.drawCard())
            players.players.forEach { player -> player.cards.addCard(deck.drawCard()) }
        }
        printCard()
    }

    fun drawPlayerCards(
        player: Player,
        isDrawCard: () -> Boolean,
        printCards: () -> Unit,
    ) {
        while (true) {
            if (isDrawCard()) {
                player.cards.addCard(deck.drawCard())
                printCards()
                if (!player.isDrawAvailable()) {
                    break
                }
            } else {
                break
            }
        }
    }

    fun drawDealerCards(
        dealer: Dealer,
        printDealerDraw: () -> Unit,
    ) {
        if (dealer.isDrawAvailable()) {
            dealer.cards.addCard(deck.drawCard())
            printDealerDraw()
        }
    }
}
