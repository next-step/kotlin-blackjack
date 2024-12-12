package blackjack

import blackjack.ui.DrawAnswer

class BlackjackService(private val deck: Deck) {
    fun initializeParticipants(playerNames: List<String>): Pair<Dealer, List<Player>> {
        val dealer = initializeDealer()
        val players = initializePlayers(playerNames)
        return dealer to players
    }

    private fun initializeDealer() = Dealer(List(2) { deck.draw() })

    private fun initializePlayers(playerNames: List<String>): List<Player> =
        playerNames.map { playerName ->
            Player(name = playerName, initialCards = List(2) { deck.draw() })
        }

    fun betAllPlayers(
        players: List<Player>,
        inputBetAmount: (player: Player) -> Long,
    ) {
        players.forEach { player -> player.bet(inputBetAmount(player)) }
    }

    fun handleAllPlayersTurn(
        players: List<Player>,
        drawAnswer: (Participant) -> DrawAnswer,
        revealParticipantHeldCards: (Participant) -> Unit,
        onBust: (Participant) -> Unit,
        onBlackjack: (Participant) -> Unit,
    ) {
        players.forEach { player ->
            handlePlayerTurn(
                participant = player,
                drawAnswer = drawAnswer,
                onHit = revealParticipantHeldCards,
                onBlackjack = onBlackjack,
                onBust = onBust,
            )
        }
    }

    private fun handlePlayerTurn(
        participant: Participant,
        drawAnswer: (Participant) -> DrawAnswer,
        onHit: (Participant) -> Unit = {},
        onBlackjack: (Participant) -> Unit = {},
        onBust: (Participant) -> Unit = {},
    ) {
        handlePlayerRunningState(participant, drawAnswer, onHit)
        handleParticipantFinishedState(participant, onBlackjack, onBust)
    }

    private fun handlePlayerRunningState(
        participant: Participant,
        drawAnswer: (Participant) -> DrawAnswer,
        onHit: (Participant) -> Unit,
    ) {
        while (!participant.isFinished()) {
            when (drawAnswer(participant)) {
                DrawAnswer.Y -> {
                    participant.hit(deck.draw())
                    onHit(participant)
                }

                DrawAnswer.N -> {
                    participant.stay()
                }
            }
        }
    }

    fun handleDealerTurn(
        dealer: Dealer,
        onUnderOverOutput: (Dealer) -> Unit,
        onBust: (Participant) -> Unit,
        onBlackjack: (Participant) -> Unit,
    ) {
        handleDealerRunningState(dealer, onUnderOverOutput)
        handleParticipantFinishedState(dealer, onBlackjack, onBust)
    }

    private fun handleDealerRunningState(
        dealer: Dealer,
        onUnderOverOutput: (Dealer) -> Unit,
    ) {
        if (dealer.shouldHit()) {
            onUnderOverOutput(dealer)
            dealer.hit(deck.draw())
        }
    }

    private fun handleParticipantFinishedState(
        participant: Participant,
        onBlackjack: (Participant) -> Unit,
        onBust: (Participant) -> Unit,
    ) {
        if (participant.isBlackjack()) {
            onBlackjack(participant)
        }
        if (participant.isBust()) {
            onBust(participant)
        }
        participant.stay()
    }

    fun calculateAllPlayerProfits(
        dealer: Dealer,
        players: List<Player>,
    ): List<Pair<Participant, Money>> {
        val dealerProfit: Pair<Dealer, Money> = calculateDealerProfit(dealer, players)
        val playerProfits: List<Pair<Player, Money>> = calculatePlayerProfits(players, dealer)
        return listOf(dealerProfit) + playerProfits
    }

    private fun calculateDealerProfit(
        dealer: Dealer,
        players: List<Player>,
    ): Pair<Dealer, Money> = dealer to dealer.calculateSelfProfit(players)

    private fun calculatePlayerProfits(
        players: List<Player>,
        dealer: Dealer,
    ): List<Pair<Player, Money>> = players.map { it to dealer.calculatePlayerProfit(it) }
}
