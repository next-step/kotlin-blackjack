package blackjack.model

import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.strategy.ShuffleStrategy
import blackjack.view.Dashboard

class BlackjackGame(private val dealer: Dealer, private val players: List<Player>, private val dashboard: Dashboard) {
    constructor(names: List<String>, shuffleStrategy: ShuffleStrategy, dashboard: Dashboard) : this(
        dealer = Dealer(shuffleStrategy),
        players = names.map(::Player),
        dashboard = dashboard
    )

    fun start() {
        players.forEach {
            dealer.receive(it)
            dealer.receive(it)
        }

        dashboard.printPlayerInitStatus(players)
    }
}
