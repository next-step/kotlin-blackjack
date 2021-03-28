package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.UserName
import blackjack.domain.player.UserNames
import blackjack.domain.player.Users

fun createPlayer(name: String) = Player(UserName(name), BettingMoney(0))

fun createPlayer(name: String, money: Int) = Player(UserName(name), BettingMoney(money))

fun createUsers(vararg name: String) = Users(listOf(Dealer()) + name.map { Player(UserName(it), BettingMoney("0")) })

fun createUserNames(vararg name: String) = UserNames(name.toList())

fun createCard(symbol: String, suit: String) = Card(CardSymbol.valueOf(symbol), CardSuit.valueOf(suit))

val SORTED_SHUFFLE = { cards: List<Card> -> cards.sortedWith(compareBy({ it.symbol }, { it.suit })) }
