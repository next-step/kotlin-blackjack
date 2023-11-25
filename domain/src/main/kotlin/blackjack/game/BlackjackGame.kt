package blackjack.game

import blackjack.dealer.Dealer
import blackjack.dealer.DealerStrategy
import blackjack.dealer.DefaultDealerStrategy
import blackjack.deck.Deck
import blackjack.player.Player

class BlackjackGame private constructor(
    val players: List<Player>,
    val dealer: Dealer = Dealer(),
    private val deck: Deck = Deck(),
) {
    init {
        require(players.toSet().isNotEmpty()) { "플레이어가 최소 한 명은 존재해야 합니다." }
    }

    fun dealInitialCards(): BlackjackGame {
        val nPlayers = List(players.size) { players[it].receiveCard(deck.drawCard(2)) }
        val nDealer = dealer.receiveCard(deck.drawCard(2))
        return BlackjackGame(
            players = nPlayers,
            dealer = nDealer,
            deck = deck
        )
    }

    class BlackjackGameBuilder {
        private val players: MutableList<Player> = mutableListOf()
        private var dealerStrategy: DealerStrategy = DefaultDealerStrategy()

        fun join(name: String) {
            players.add(Player(name = name))
        }

        fun join(vararg names: String) {
            names.forEach {
                join(it)
            }
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
