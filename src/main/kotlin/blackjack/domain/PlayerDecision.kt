package blackjack.domain

interface PlayerDecision {
    fun shouldDrawCard(playerName: String): Boolean
}