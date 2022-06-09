package blackjack.domain.player

import blackjack.domain.card.Deck

class Players(val players: List<Player>) {

    init {
        require(players.isNotEmpty()) {
            "플레이어는 한 명 이상이어야 합니다."
        }
    }

    fun addTwoCard(deck: Deck) {
        players.forEach { player ->
            repeat(2) { player.addCard(deck.draw()) }
        }
    }

    fun hittablePlayers(): List<Player> {
        return players.filterNot(Player::isEnd)
    }

    fun isEnd(): Boolean {
        return players.all(Player::isEnd)
    }

    fun contains(player: Player): Boolean {
        return players.contains(player)
    }
}
