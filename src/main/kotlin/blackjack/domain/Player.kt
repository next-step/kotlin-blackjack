package blackjack.domain

class Player(
    val name: PlayerName,
    val hand: Hand,
) {
    var status: PlayerStatus = PlayerStatus.STAY
        private set

    fun hit(card: Card) {
        hand.add(card)

        if (hand.bust()) {
            status = PlayerStatus.BUST
            return
        }

        if (hand.total() == BLACKJACK) {
            status = PlayerStatus.BLACKJACK
            return
        }

        status = PlayerStatus.HIT
    }

    fun stay() {
        status = PlayerStatus.STAY
    }

    fun total(): Int {
        return hand.total()
    }

    companion object {
        fun of(name: PlayerName, openCards: OpenCards): Player {
            return Player(name, Hand.create(openCards))
        }
    }
}

class PlayerName private constructor (val value: String) {
    companion object {
        const val MAX_NAME_SIZE = 10
        fun from(value: String): PlayerName {
            require(value.length <= MAX_NAME_SIZE) { println("이름은 최대 ${MAX_NAME_SIZE} 글자 입니다.") }
            return PlayerName(value)
        }
    }
}

enum class PlayerStatus {
    SURRENDER,
    STAY,
    HIT,
    BLACKJACK,
    BUST
}
