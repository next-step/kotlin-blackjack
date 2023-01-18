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

    fun calculateScore(gameRule: GameRule) {
        values.forEach {
            val summed = gameRule.sumOfCards(it)
            it.setSummedCardNumbers(summed)
            printSummedCards(it.name, it.cards, it.summedCardNumbers)
        }
    }
}