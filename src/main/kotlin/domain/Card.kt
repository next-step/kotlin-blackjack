package domain

import enum.Rank
import enum.Suit

data class Card(val suit: Suit, val rank: Rank) {
    override fun toString(): String {
        return "${rank.label}${suit.label}"
    }
}
