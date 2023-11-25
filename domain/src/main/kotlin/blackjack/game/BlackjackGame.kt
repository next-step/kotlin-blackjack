package blackjack.game

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.dealer.DealerStrategy
import blackjack.dealer.DefaultDealerStrategy
import blackjack.deck.Deck
import blackjack.player.Player

class BlackjackGame private constructor(
    players: List<Player>,
    dealer: Dealer = Dealer(),
    private val deck: Deck = Deck(),
) {
    init {
        require(players.toSet().isNotEmpty()) { "플레이어가 최소 한 명은 존재해야 합니다." }
    }

    var state: GameState = GameState.InitialDeal(players, dealer)
        private set

    val players: List<Player> get() = state.players
    val dealer: Dealer get() = state.dealer

    fun dealInitialCards() {
        check(state is GameState.InitialDeal) { "Initial Deal 상태가 아닙니다." }
        val nPlayers = List(players.size) { players[it].receiveCard(deck.drawCard(2)) }
        val nDealer = dealer.receiveCard(deck.drawCard(2))
        state = GameState.PlayerTurn(nPlayers, nDealer, currentPlayerIndex = 0)
    }

    fun dealPlayerTurn(player: Player, isDeal: Boolean) {
        val playerTurnState = state as? GameState.PlayerTurn ?: throw IllegalStateException("Player Turn이 아닙니다.")
        require(players.contains(player)) { "${player.name}이라는 플레이어는 없습니다." }
        require(player == playerTurnState.currentPlayer) { "현재 턴은 ${player.name}의 턴이 아닙니다." }

        if (isDeal.not()) {
            // 다음 플레이어로 넘어감
            moveToNextPlayerOrDealerTurn(playerTurnState.currentPlayerIndex)
        } else {
            // 카드 받기
            check(player.canHit() == BlackJackAction.HIT) { "해당 플레이어는 더 이상 카드를 받을 수 없습니다." }
            val nPlayers = players.map { if (it == player) it.receiveCard(deck.drawCard()) else it }
            state = GameState.PlayerTurn(nPlayers, dealer, playerTurnState.currentPlayerIndex)
        }
    }

    fun dealDealerTurn(): BlackJackAction {
        check(state is GameState.DealerTurn) { "Dealer Turn이 아닙니다." }
        return when (dealer.decideAction(deck)) {
            BlackJackAction.HIT -> {
                state = GameState.End(
                    players,
                    dealer.receiveCard(deck.drawCard())
                )
                BlackJackAction.HIT
            }

            BlackJackAction.STAND -> {
                state = GameState.End(players, dealer)
                BlackJackAction.STAND
            }
        }
    }

    fun calculateResult(): Map<BlackjackParticipant, BlackjackResult> {
        val results = mutableMapOf<BlackjackParticipant, BlackjackResult>()
        results[dealer] = calculateDealerResult()
        players.forEach {
            results[it] = calculatePlayerResult(it)
        }
        return results
    }

    fun showPlayerCards(playerName: String): List<Card> {
        val player = state.players.find { it.name == playerName }
            ?: throw IllegalArgumentException("${playerName}이라는 플레이어는 없습니다.")
        return player.cards
    }

    private fun calculateDealerResult(): BlackjackResult {
        val dealerScore = dealer.calculateBestValue()
        var win = 0
        var loss = 0
        players.forEach {
            if (dealerScore > 21) loss++
            else if (it.calculateBestValue() > 21) win++
            else if (dealerScore > it.calculateBestValue()) win++
            else if (dealerScore <= it.calculateBestValue()) loss++
        }
        return BlackjackResult(win, loss)
    }

    private fun calculatePlayerResult(player: Player): BlackjackResult {
        val playerScore = player.calculateBestValue()
        val dealerScore = dealer.calculateBestValue()
        return if (dealerScore > 21) {
            BlackjackResult(1, 0)
        } else if (playerScore > 21) {
            BlackjackResult(0, 1)
        } else if (playerScore >= dealerScore) {
            BlackjackResult(1, 0)
        } else {
            BlackjackResult(0, 1)
        }
    }

    private fun moveToNextPlayerOrDealerTurn(currentPlayerIndex: Int) {
        val nextPlayerIndex = (currentPlayerIndex + 1) % players.size
        state = if (nextPlayerIndex == 0) {
            GameState.DealerTurn(players, dealer)
        } else {
            GameState.PlayerTurn(players, dealer, nextPlayerIndex)
        }
    }

    class BlackjackGameBuilder {
        private val players: MutableList<Player> = mutableListOf()
        private var dealerStrategy: DealerStrategy = DefaultDealerStrategy()

        fun join(name: String) {
            players.add(Player(name = name))
        }

        fun join(names: List<String>) {
            names.forEach {
                join(it)
            }
        }

        fun dealerStrategy(strategy: DealerStrategyType) {
            when (strategy) {
                DealerStrategyType.DEFAULT_DEALER_STRATEGY -> dealerStrategy = DefaultDealerStrategy()
                // 다른 전략 추가
            }
        }

        fun build(): BlackjackGame {
            return BlackjackGame(
                players = players.toList(),
                dealer = Dealer(dealerStrategy = dealerStrategy)
            )
        }
    }
}

enum class DealerStrategyType {
    DEFAULT_DEALER_STRATEGY
}

fun blackjackOpen(block: BlackjackGame.BlackjackGameBuilder.() -> Unit): BlackjackGame =
    BlackjackGame.BlackjackGameBuilder().apply(block).build()
