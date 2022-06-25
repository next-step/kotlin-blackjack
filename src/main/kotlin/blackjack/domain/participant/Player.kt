package blackjack.domain.participant

import blackjack.domain.deck.Card

class Player(
    val playerName: PlayerName,
    val hand: MutableList<Card> = mutableListOf(),
) {
    fun receiveCard(card: Card) {
        hand.add(card)
    }

    fun getPlayerNameValue(): String = playerName.value

    companion object {
        fun from(nameValue: String): Player = Player(playerName = PlayerName(value = nameValue))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (playerName != other.playerName) return false

        return true
    }

    override fun hashCode(): Int {
        return playerName.hashCode()
    }
}
