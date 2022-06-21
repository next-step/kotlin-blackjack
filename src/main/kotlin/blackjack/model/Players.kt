package blackjack.model

class Players(val values: List<Player>) {
    fun findNotOver(): List<Player> {
        return values.filter { !it.stay }
    }

    fun setStay(player: Player): Players {
        return Players(
            values.map {
                if (player.name.equals(it.name)) {
                    player.setStay()
                } else {
                    it
                }
            }
        )
    }

    fun hit(player: Player, cardList: List<Card>): Players {
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
        return values.all { it.stay } // TODO stay 가 아닌 stay or bust 로 체크
    }

    fun find(name: String): Player? {
        return values.find { it.name == name }
    }
}
