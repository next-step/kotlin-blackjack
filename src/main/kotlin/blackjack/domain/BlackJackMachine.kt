package blackjack.domain

import blackjack.common.Policy.INITIAL_CARD_COUNT

class BlackJackMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val players: List<Participant>,
) {
    fun initialize() {
        players.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.addCard(cardDeck.hit())
            }
        }
    }

    fun execute(
        retryFunc: (player: Participant) -> Boolean,
        playerCardResultFunc: (player: Participant) -> Unit,
    ) {
        players.forEach {
            hitOrNot(it, retryFunc, playerCardResultFunc)
        }
    }

    private tailrec fun hitOrNot(
        player: Participant,
        retryFunc: (player: Participant) -> Boolean,
        printFunc: (player: Participant) -> Unit,
    ) {
        if (player.isBlackJack())
            return

        if (player.isMaxScore())
            return

        if (player.isBust())
            return

        if (!retryFunc(player))
            return

        player.addCard(cardDeck.hit())
        printFunc(player)
        hitOrNot(player, retryFunc, printFunc)
    }
}
