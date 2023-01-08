package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.User
import blackjack.domain.state.FirstTurn
import blackjack.io.Input
import blackjack.io.Output

class Game(private val input: Input, private val output: Output) {
    private val deck: Deck = Deck()

    init {
        deck.shuffle()
    }

    fun run() {
        val names = input.getPlayers()
        output.printDistribute(names)

        val dealer = Dealer(FirstTurn.draw(deck.draw(), deck.draw()))

        val users = names
            .asSequence()
            .map { name -> User(name, FirstTurn.draw(deck.draw(), deck.draw())) }
            .map { it.bat(input.getBet(it)) }
            .toList()

        output.printUsersCard(users)
        output.printDealerCard(dealer)

        draw(users, dealer)
    }

    private fun draw(users: List<User>, dealer: Dealer) {
        return result(users.map { userDraw(it) }, dealerDraw(dealer))
    }

    private fun result(users: List<User>, dealer: Dealer) {
        output.printDealerHandWithScore(dealer)
        output.printUsersHandWithScore(users)
        output.printProfitHeader()
        output.printProfit(dealer, users.sumOf { -it.profit(dealer) })
        users.forEach {
            output.printProfit(it, it.profit(dealer))
        }
    }

    private fun dealerDraw(dealer: Dealer): Dealer {
        if (!dealer.canDraw()) {
            return dealer
        }

        if (dealer.isDealersHit()) {
            output.printDealerDraw()
            return dealer.draw(deck.draw())
        }

        return dealer.stay()
    }

    private fun userDraw(user: User): User {
        if (!user.canDraw()) {
            return user
        }

        if (input.moreDraw(user)) {
            val drawUser = user.draw(deck.draw())
            output.printPlayerCard(drawUser)
            return this.userDraw(drawUser)
        }

        return user.stay()
    }
}
