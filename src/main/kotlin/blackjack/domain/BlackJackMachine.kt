package blackjack.domain

import blackjack.common.Policy.INITIAL_CARD_COUNT

class BlackJackMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val players: List<Player>,
    private val dealer: Dealer,
) {
    fun initialize() {
        players.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.hit(cardDeck.pop())
            }
        }

        repeat(INITIAL_CARD_COUNT) {
            dealer.hit(cardDeck.pop())
        }
    }

    fun executePlayer(
        retryFunc: (player: Player) -> Boolean,
        playerCardResultFunc: (player: Player) -> Unit,
    ) {
        players.forEach { hitOrNot(it, retryFunc, playerCardResultFunc) }
    }

    fun executeDealer(
        dealerCardResultFunc: (dealer: Dealer) -> Unit,
    ) {
        dealer.takeIf { it.canHit() }
            ?.let {
                it.hit(cardDeck.pop())
                dealerCardResultFunc(it)
            }
    }

    private tailrec fun hitOrNot(
        player: Player,
        retryFunc: (player: Player) -> Boolean,
        printFunc: (player: Player) -> Unit,
    ) {
        if (!player.canHit())
            return

        if (!retryFunc(player))
            return

        player.hit(cardDeck.pop())
        printFunc(player)
        hitOrNot(player, retryFunc, printFunc)
    }
}
