package domain.game

import domain.card.Cards
import domain.card.Deck
import domain.player.Dealer
import domain.player.Player
import domain.player.Players
import domain.state.StartState
import domain.state.State

class BlackjackGame(private val deck: Deck, playerBetAmounts: Map<String, Int>) {

    val players: Players

    val dealer: Dealer

    init {
        require(PLAYERS_RANGE.contains(playerBetAmounts.keys.size)) { "플레이어 수는 1 ~ 8명이어야 합니다." }
        this.players = Players(initPlayers(playerBetAmounts))
        val dealerState = StartState.start(cards = initCards())
        this.dealer = Dealer(state = dealerState)
    }

    private fun initPlayers(playerBetAmounts: Map<String, Int>) = playerBetAmounts.map { (name, betAmount) ->
        val state = StartState.start(cards = initCards(), betAmount = betAmount)
        Player(name = name, state = state)
    }

    private fun initCards() = Cards(listOf(deck.issueCard(), deck.issueCard()))

    fun gameStart(
        isIssueCard: (player: Player) -> Boolean,
        showMessage: (player: Player) -> Unit,
    ) {
        players.forEach { player ->
            playGame(player = player, isIssueCard, showMessage = showMessageWithCheck(showMessage))
        }
        playGame(
            player = dealer,
            isIssueCard = { dealer.isDrawable() },
            showMessage = showMessageWithCheck(showMessage),
        )
    }

    private fun playGame(
        player: Player,
        isIssueCard: (player: Player) -> Boolean,
        showMessage: (player: Player) -> Unit,
    ) {
        while (!player.isTerminated()) {
            gameProgress(isIssueCard, player)

            showMessage(player)
        }
    }

    private fun gameProgress(isIssueCard: (player: Player) -> Boolean, player: Player) {
        when (isIssueCard(player)) {
            true -> issueCard(player)
            else -> player.stop()
        }
    }

    private fun showMessageWithCheck(showMessage: (player: Player) -> Unit): (player: Player) -> Unit =
        { if (!it.isTerminated()) showMessage(it) }

    private fun issueCard(player: Player): State {
        return player.draw(this.deck.issueCard())
    }

    fun getPlayersRevenues(): RevenueResult {
        val playersRevenues = players.associate { player -> player.name to player.getRevenue(dealer) }
        val dealerRevenue = playersRevenues.values.sumOf { -it }
        return RevenueResult(dealerRevenue = dealerRevenue, playersRevenues = playersRevenues)
    }

    companion object {
        private const val MAX_PLAYER_SIZE = 8
        private const val MIN_PLAYER_SIZE = 1
        private val PLAYERS_RANGE = IntRange(MIN_PLAYER_SIZE, MAX_PLAYER_SIZE)
        const val BLACKJACK_GAME_DECK_SIZE = 6
    }
}
