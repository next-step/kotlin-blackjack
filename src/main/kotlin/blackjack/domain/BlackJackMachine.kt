package blackjack.domain

import blackjack.common.Policy.INITIAL_CARD_COUNT

class BlackJackMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val participants: List<Participant>,
) {
    fun initialize() {
        participants.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.hit(cardDeck.pop())
            }
        }
    }

    fun execute(
        retryFunc: (player: Player) -> Boolean,
        playerCardResultFunc: (player: Player) -> Unit,
        dealerCardResultFunc: (dealer: Dealer) -> Unit,
    ) {
        participants
            .filterIsInstance<Player>()
            .forEach {
                hitOrNot(it, retryFunc, playerCardResultFunc)
            }

        participants
            .filterIsInstance<Dealer>()
            .filter { it.canHit() }
            .forEach {
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
