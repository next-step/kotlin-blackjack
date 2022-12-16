package blackjack.domain

class PockerMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val dealer: Dealer,
    private val players: List<Player>
) {
    fun initialize() {
        List(BASIC_CARD_COUNT) {
            players.map { player -> player.addCard(dealer.pickCard(cardDeck)) }
        }
    }

    fun addCard(
        retryFunc: (person: Player) -> Boolean,
        printFunc: (person: Player) -> Unit
    ) {
        players.map { player ->
            when (player) {
                is Dealer -> player.pickIfRequired(cardDeck)
                is Participant -> pickOrNot(player, retryFunc, printFunc)
            }
        }
    }

    fun getGameResult(): GameResult {
        return GameResult(
            dealerName = dealer.name,
            participantResult = players.filterIsInstance<Participant>().map { ParticipantResult(it.name, it.getGameResult(dealer)) }
        )
    }

    private fun pickOrNot(
        player: Player,
        retryFunc: (player: Player) -> Boolean,
        printFunc: (player: Player) -> Unit
    ) {
        while (player.getScore() < MAXIMUM_SCORE) {
            if (!retryFunc(player)) {
                printFunc(player)
                return
            }
            val card = dealer.pickCard(cardDeck)
            player.addCard(card)
            printFunc(player)
        }
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
        private const val MAXIMUM_SCORE = 21
    }
}
