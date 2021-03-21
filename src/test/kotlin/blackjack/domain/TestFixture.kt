package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Player
import blackjack.domain.player.UserName
import blackjack.domain.player.Users

fun createPlayer(name: String) = Player(UserName(name))

fun createPlayers(vararg name: String) = Users(name.map { Player(UserName(it)) }.toList())

fun createCard(symbol: String, suit: String) = Card(CardSymbol.valueOf(symbol), CardSuit.valueOf(suit))
