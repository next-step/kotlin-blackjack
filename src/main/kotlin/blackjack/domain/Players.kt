package blackjack.domain

import blackjack.domain.Game.Companion.BLACKJACK_SCORE
import java.math.BigDecimal

data class Players(private val players: List<Player>) {
    var turn = 0
        private set

    fun setStake(amount: Int) {
        currentPlayer().setBetMoney(amount)
        turn++
    }

    fun setUpWithCards(cards: List<Card>) {
        cards.forEachIndexed { nth, nthCard ->
            if (nth < this.size()) players[nth].draw(nthCard)
            if (nth >= this.size()) players[nth - this.size()].draw(nthCard)
        }
    }

    fun chooseToDraw(reply: String, deck: Deck): Boolean {
        val player = players[turn]
        val state = player.getStateBy(reply)

        player.apply {
            if (isHit(state) &&
                !isBust(state) &&
                !isBlackJack(state)
            ) {
                draw(deck.provideCard())
                return false
            }
        }
        turn++
        return turn == size()
    }

    fun winIfStillAlive() {
        players.forEach { player ->
            when (player.getStateBy()) {
                State.BUST -> player.loseAll()
                else -> player.earnDoubleMoney()
            }
        }
    }

    fun calculateProfitWhenDealerIsBlackJack() {
        players.forEach { player ->
            when (player.getStateBy()) {
                State.BLACKJACK -> player.getPrincipal()
                else -> player.loseAll()
            }
        }
    }

    fun sumOfAllProfits(): BigDecimal = players.map { it.profit }.sumByBigDecimal { it.amount }

    fun compareWith(dealerScore: Int) {
        players.filter { player ->
            player.score() in (dealerScore + 1)..BLACKJACK_SCORE &&
                dealerScore != BLACKJACK_SCORE &&
                !player.isBlackJack(player.getStateBy())
        }.forEach { it.earnDoubleMoney() }

        players.filter { player ->
            player.score() <= dealerScore || player.isBust(player.getStateBy())
        }.forEach { it.loseAll() }
    }

    fun findPlayer(nth: Int): Player = players[nth]

    fun currentPlayer(): Player = players[turn]

    fun statesOfCards(): String {
        return players.joinToString("\n") { "${it}카드: ${it.stateOfCards()}" }
    }

    fun size() = players.size

    fun resetTurn() {
        turn = 0
    }

    override fun toString(): String {
        return players.joinToString()
    }

    private inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
        var sum: BigDecimal = BigDecimal.ZERO
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }
}
