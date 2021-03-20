package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players

fun createPlayer(name: String) = Player(PlayerName(name))

fun createPlayers(vararg name: String) = Players(name.map { Player(PlayerName(it)) }.toList())

fun createCard(symbol: String, suit: String) = Card(CardSymbol.valueOf(symbol), CardSuit.valueOf(suit))
