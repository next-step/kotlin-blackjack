package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.Player
import blackjack.domain.player.Players

fun cards(vararg cards: Card) = Cards(cards.toList())

fun players(vararg players: Player) = Players(players.toList())
