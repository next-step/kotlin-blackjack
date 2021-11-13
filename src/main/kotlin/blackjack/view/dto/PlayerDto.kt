package blackjack.view.dto

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.Players

data class PlayersDto(val players: List<PlayerDto>) : List<PlayerDto> by players {

    constructor(players: Players) : this(players.players.map { PlayerDto(it) })
}

data class PlayerDto(val name: String, val cards: List<CardDto>, val score: Int) {

    constructor(player: Player) : this(player.name.value, player.hand.cards.map(::CardDto), player.score.value)
}

data class CardDto(val name: String) {

    constructor(card: Card) : this("${card.symbol}${card.type}")
}
