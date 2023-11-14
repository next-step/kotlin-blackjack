package dto

import domain.Card

data class GameStateDTO(
    val playerHands: Map<String, List<Card>>,
    val dealerHand: List<Card>,
)
