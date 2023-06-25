package domain.game

import domain.card.Cards
import domain.card.Deck
import domain.player.Dealer
import domain.player.Player
import domain.player.Players
import domain.state.State
import domain.state.TerminationState
import domain.dto.WinLoseDrawResult

class BlackjackGame(private val deck: Deck) {

    lateinit var players: Players
        private set

    lateinit var dealer: Dealer
        private set

    fun initGame(playerNames: List<String>): Players {
        require(PLAYERS_RANGE.contains(playerNames.size)) { "플레이어 수는 1 ~ 8명이어야 합니다." }
        this.players = createPlayers(playerNames)
        this.dealer = Dealer(cards = initCards())
        return this.players
    }

    private fun createPlayers(playerNames: List<String>) =
        Players(playerNames.map { Player(it, cards = initCards()) })

    private fun initCards() = Cards(listOf(deck.issueCard(), deck.issueCard()))

    fun gameStart(
        isIssueCard: (playerName: String) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        players.forEach { playGame(player = it, isIssueCard, showPlayerCards) }
    }

    private fun playGame(
        player: Player,
        isIssueCard: (playerName: String) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        while (!this.isTerminatedPlayer(player)) {
            gameProgress(isIssueCard, player)

            showPlayerCards(player)
        }
    }

    private fun gameProgress(isIssueCard: (playerName: String) -> Boolean, player: Player) {
        when (isIssueCard(player.name)) {
            true -> this.issueCard(player)
            else -> this.stopIssueCard(player)
        }
    }

    fun issuedCardForDealer(): Boolean {
        val beforeCardSize = dealer.cards.size
        this.issueCard(dealer)
        return dealer.cards.size > beforeCardSize
    }

    private fun isTerminatedPlayer(player: Player): Boolean {
        return player.state is TerminationState
    }

    private fun issueCard(player: Player): State {
        return player.draw(this.deck.issueCard())
    }

    private fun stopIssueCard(player: Player): State {
        return player.stop()
    }

    fun getGameWinLoseDrawResult(): WinLoseDrawResult {
        val playerGameResultMap = players.groupBy { it.getPlayerGameResult(dealer) }
        return WinLoseDrawResult(playerResultMap = playerGameResultMap)
    }

    companion object {
        private const val MAX_PLAYER_SIZE = 8
        private const val MIN_PLAYER_SIZE = 1
        private val PLAYERS_RANGE = IntRange(MIN_PLAYER_SIZE, MAX_PLAYER_SIZE)
        const val BLACKJACK_GAME_DECK_SIZE = 6
    }
}
