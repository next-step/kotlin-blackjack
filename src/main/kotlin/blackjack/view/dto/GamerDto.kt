package blackjack.view.dto

import blackjack.domain.card.Card
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers

data class GamersDto(val gamers: List<GamerDto>) : List<GamerDto> by gamers {

    constructor(gamers: Gamers) : this(gamers.gamers.map { GamerDto(it) })
}

data class GamerDto(val name: String, val cards: List<CardDto>, val score: Int) {

    constructor(gamer: Gamer) : this(gamer.name.value, gamer.hand.cards.cards.map(::CardDto), gamer.score.value)
}

data class CardDto(val name: String) {

    constructor(card: Card) : this("${card.symbol}${card.type}")
}
