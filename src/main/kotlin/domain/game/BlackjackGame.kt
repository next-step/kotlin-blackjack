package domain.game

import domain.card.Deck
import domain.player.Dealer
import domain.player.Player
import domain.player.Players
import domain.state.State
import domain.state.TerminationState

class BlackjackGame(private val deck: Deck) {

    lateinit var players: Players
        private set

    lateinit var dealer: Dealer
        private set

    fun initGame(playerNames: List<String>): Players {
        require(PLAYERS_RANGE.contains(playerNames.size)) { "플레이어 수는 1 ~ 8명이어야 합니다." }
        this.players = Players(
            playerNames.map {
                Player(it, deck.issueCard(), deck.issueCard())
            },
        )
        this.dealer = Dealer(deck.issueCard(), deck.issueCard())
        return this.players
    }

    fun gameStart(playGame: (players: Players) -> Unit) {
        playGame(players)
    }

    fun issuedCardForDealer(): Boolean =
        if (dealer.isDrawable()) {
            this.issueCard(dealer)
            true
        } else {
            false
        }

    fun isTerminatedPlayer(player: Player): Boolean {
        return player.state is TerminationState
    }

    fun issueCard(player: Player): State {
        return player.draw(this.deck.issueCard())
    }

    fun stopIssueCard(player: Player): State {
        return player.stop()
    }

    companion object {
        private const val MAX_PLAYER_SIZE = 8
        private const val MIN_PLAYER_SIZE = 1
        private val PLAYERS_RANGE = IntRange(MIN_PLAYER_SIZE, MAX_PLAYER_SIZE)
        const val BLACKJACK_GAME_DECK_SIZE = 6
    }
}
