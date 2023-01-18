package blackjack.domains.participants

import blackjack.GameRule
import blackjack.views.Output.printSummedCards

@JvmInline
value class Gamers(private val values: List<User>) {
    fun getDealer(): Dealer {
        return values.single { it is Dealer } as Dealer
    }

    fun getPlayers(): List<Player> {
        return values.filterIsInstance<Player>()
    }

    fun drawCard(gameRule: GameRule) {
        values.forEach { gameRule.drawCard(it) }
    }

    fun printScores() {
        values.forEach {
            printSummedCards(it.name, it.cards, it.summedCardNumbers)
        }
    }
}
