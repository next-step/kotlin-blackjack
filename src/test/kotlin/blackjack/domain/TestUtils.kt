package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers

fun cards(vararg cards: Card) = Cards(cards.toList())

fun gamePlayers(vararg players: GamePlayer) = GamePlayers(players.toList())
