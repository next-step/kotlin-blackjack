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
        while (isDrawCard() && !player.isDrawAvailable()) {
            player.cards.addCard(deck.drawCard())
            printCards()
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

    fun decideGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach { player ->
            dealer.minusMoney(calculateAndApplyPlayerResult(dealer, player))
        }
    }

    private fun calculateAndApplyPlayerResult(
        dealer: Dealer,
        player: Player,
    ): Int {
        val multiplier = calculateWinMultiplier(dealer, player)
        player.multiplyMoney(multiplier)
        return player.money
    }

    private fun calculateWinMultiplier(
        dealer: Dealer,
        player: Player,
    ): Double {
        if (player.getCardSize() > INITIAL_DRAW_CARD_SIZE && player.isBust()) return -1.0
        if (dealer.isBust()) return 1.0
        if (isBothBlackJack(dealer, player)) return 1.0
        if (player.isPlayerBlackJack()) return 1.5
        if (dealer.getScore() < player.getScore()) return 1.0
        return -1.0
    }

    private fun isBothBlackJack(
        dealer: Dealer,
        player: Player,
    ): Boolean = dealer.isBlackJack() && player.getCardSize() == INITIAL_DRAW_CARD_SIZE && player.isBlackJack()

    private fun Player.isPlayerBlackJack(): Boolean = getCardSize() == INITIAL_DRAW_CARD_SIZE && isBlackJack()
}
