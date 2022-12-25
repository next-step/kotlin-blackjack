package blackjack.view

data class PlayerResponse(val name: String, val cards: List<CardResponse>)
data class CardResponse(val pattern: String, val name: String)
