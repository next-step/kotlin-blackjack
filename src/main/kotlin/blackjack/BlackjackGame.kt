package blackjack

import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {

    private var dealer: Dealer
    private var players: Players
    private var nicknames: List<String> = InputView.getNicknames()

    init {
        players = Players(nicknames.map { Player(it) })
        dealer = Dealer()
        dealer.ready()
    }

    fun start() {
        repeat(Cards.INITIAL_DEAL_SIZE) {
            players.values.forEach {
                it.getCard(dealer.dealCard())
            }
        }
        OutputView.printCardDealingHeader(nicknames.joinToString(", "), Cards.INITIAL_DEAL_SIZE)
        players.values.forEach {
            OutputView.printCardDealing(
                it.name,
                it.cards.value.joinToString(", ") { card -> card.toString() }
            )
        }

        process(dealer, players)

        players.values.forEach {
            OutputView.printGameScore(
                nickname = it.name,
                cards = it.cards.value.joinToString(", ") { card -> card.toString() },
                score = it.getScore()
            )
        }
    }

    private fun process(dealer: Dealer, players: Players) {
        println()
        while (players.withHit().isNotEmpty()) {
            players.withHit().first().playGame(dealer)
        }
        println()
    }

    private fun Player.playGame(dealer: Dealer) {
        if (InputView.askHitOrStand(this.name)) {
            this.getCard(dealer.dealCard())
            OutputView.printCardDealing(this.name, this.cards.value.joinToString(",") { card -> card.toString() })
        } else {
            this.turnStand()
        }
    }
}

fun main() {
    BlackjackGame().start()
}
