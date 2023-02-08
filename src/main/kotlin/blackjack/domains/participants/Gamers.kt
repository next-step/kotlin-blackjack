package blackjack.domains.participants

import blackjack.CardDraw
import blackjack.views.Output.printSummedCards

@JvmInline
value class Gamers(val values: List<User>) {
    fun getDealer(): Dealer {
        return values.single { it is Dealer } as Dealer
    }

    fun getPlayers(): List<Player> {
        return values.filterIsInstance<Player>()
    }

    fun drawCard(cardDraw: CardDraw) {
        values.forEach { cardDraw.draw(it) }
    }

    fun printScores() {
        values.forEach {
            printSummedCards(it.name, it.cards, it.summedCardNumbers)
        }
    }
}
