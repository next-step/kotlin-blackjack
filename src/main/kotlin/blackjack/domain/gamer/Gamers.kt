package blackjack.domain.gamer

import blackjack.domain.deck.Deck
import blackjack.exception.NotExistDealerException

class Gamers private constructor(
    value: List<Gamer>,
) {
    val value = value.toList()

    init {
        if (value.first() !is Dealer) {
            throw NotExistDealerException()
        }
    }

    fun prepare(deck: Deck): Gamers {
        val preparedGamers = mutableListOf<Gamer>()
        for (gamer in value) {
            val preparedGamer = gamer.prepare(deck)
            preparedGamers.add(preparedGamer)
        }
        return Gamers(preparedGamers)
    }

    fun getDealer(): Dealer {
        val dealer = value.first()
        return Dealer.init(dealer.name, dealer.state)
    }

    fun getPlayers(): List<Player> {
        return value.subList(PLAYER_INDEX, value.size)
            .map { Player.init(it.name, it.state) }
    }

    companion object {
        private const val PLAYER_INDEX = 1

        fun from(gamers: List<Gamer>): Gamers {
            return Gamers(gamers)
        }
    }
}
