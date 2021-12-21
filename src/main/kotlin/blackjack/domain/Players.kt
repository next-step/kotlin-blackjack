package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.strategy.draw.DrawStrategy

class Players private constructor(players: List<Player>) {

    val players = players.toList()

    fun drawCardEachPlayer(cardDeck: CardDeck, drawStrategy: DrawStrategy): Players {
        return players.map {
            it.draw(cardDeck, drawStrategy)
        }.let {
            Players(it)
        }
    }

    companion object {

        fun getPlayerListByNames(names: List<String>): List<GamePlayer> {
            return names.map { GamePlayer(Name.from(it)) }
        }

        fun from(list: List<Player>): Players {
            return Players(list)
        }
    }
}
