package blackjack.model

class Players(val values: List<Player>) {
    fun findNotOver(): List<Player> {
        return values.filter { !it.gameOver }
    }

    fun setGameOver(player: Player): Players {
        return Players(
            values.map {
                if (player.name.equals(it.name)) {
                    player.gameOver()
                } else {
                    it
                }
            }
        )
    }

    fun giveCard(player: Player, cardList: List<Card>): Players {
        return Players(
            values.map {
                if (player.name.equals(it.name)) {
                    player.addCards(cardList)
                } else {
                    it
                }
            }
        )
    }

    fun isAllOver(): Boolean {
        return values.all { it.gameOver }
    }

    fun find(name: String): Player? {
        return values.find { it.name == name }
    }
}
