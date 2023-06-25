package blackjack.domain.dsl

import blackjack.domain.Gamer
import blackjack.domain.Players

@BuilderMarker
class GamersBuilder : Builder<Players> {
    private val names: MutableList<String> = mutableListOf()
    private val bets: MutableList<Double> = mutableListOf()

    fun name(value: String) {
        names.add(value)
    }

    fun bet(value: Double) {
        bets.add(value)
    }

    override fun build(): Players {
        check(names.size == bets.size) {
            "참가자의 수와 베팅액의 수가 맞지 않습니다. 참가자: $names, 베팅액: $bets"
        }

        return names.zip(bets)
            .map { Gamer(name = it.first, bet = it.second) }
            .let { Players(values = it) }
    }
}
