package blackjack.domain

import java.lang.RuntimeException

class Player(
    val name: PlayerName,
    val hand: Hand,
) {
    fun burst(): Boolean = hand.burst()
    fun hit(card: Card) {
        if (burst()) {
            throw RuntimeException()
        }
        hand.add(card)
    }
    fun bestHandTotal(): Int {
        return hand.bestHandTotal()
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
