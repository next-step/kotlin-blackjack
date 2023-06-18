package blackjack.domain.view.model

open class PlayerView(val name: String, val cards: List<CardView>)

class PlayerViewResult(name: String, cards: List<CardView>, val score: Int): PlayerView(name = name, cards = cards)
