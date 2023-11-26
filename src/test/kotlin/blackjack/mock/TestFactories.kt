package blackjack.mock

import blackjack.controller.ResultProcessor
import blackjack.domain.Action
import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.GameTable
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players

fun card(rank: Rank, suit: Suit = Suit.CLUB): Card = Card(suit, rank)

fun hand(vararg cards: Card): Hand = Hand(cards.toMutableList())

fun deck(vararg cards: Card): Deck = Deck(cards.toMutableList().let(::ArrayDeque))

fun deck(cards: List<Card>): Deck = Deck(cards.let(::ArrayDeque))

fun player(
    name: String = "kim",
    action: Action = Action.HIT,
    hand: Hand = Hand(),
) = Player(PlayerName(name), { action }, hand)

fun players(vararg players: Player) = players
    .toList()
    .let { it.ifEmpty { listOf(player("kim"), player("lee")) } }
    .let(::Players)

fun blackJackGame(
    inputAction: Action = Action.HIT,
    resultProcessor: ResultProcessor = ResultProcessor(),
    dealer: Dealer = Dealer(),
    players: Players = players(player("kim"), player("lee")),
) = BlackJackGame(InputProcessorMock(action = inputAction), resultProcessor, GameTable(dealer, players))
