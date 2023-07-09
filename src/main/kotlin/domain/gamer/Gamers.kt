package domain.gamer

import domain.card.CardDeck
import domain.gamer.dealer.Dealer
import domain.gamer.player.Player
import domain.gamer.player.Players

class Gamers(private val list: List<Gamer>) {

    val dealer: Dealer
        get() = list.filterIsInstance<Dealer>().first()

    val players: Players
        get() = Players(list.filterIsInstance<Player>())

    fun hit(cardDeck: CardDeck) {
        list.forEach {
            it.hit(cardDeck)
        }
    }

    fun gamerToHit(): Gamer? {
        return list
            .firstOrNull {
                it.isHit
            }
    }

    companion object {
        fun of(dealer: Dealer, players: Players): Gamers {
            return Gamers(players.list.plus(dealer))
        }
    }
}
