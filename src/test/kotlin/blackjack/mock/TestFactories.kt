package blackjack.mock

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.GameTable
import blackjack.domain.batting.Amount
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import blackjack.domain.result.game.Profit

fun card(rank: Rank = Rank.TEN, suit: Suit = Suit.CLUB): Card = Card(suit, rank)

fun hand(vararg cards: Card): Hand = Hand(cards.toMutableList())

fun deck(vararg cards: Card): Deck = Deck(ArrayDeque(cards.toMutableList()))

fun deck(cards: List<Card>): Deck = Deck(ArrayDeque(cards))

fun player(
    name: String = "kim",
    action: Action = Action.HIT,
    hand: Hand = Hand(),
) = Player(PlayerName(name), { action }, hand)

fun players(vararg players: Player) = players
    .toList()
    .let { it.ifEmpty { listOf(player("kim"), player("lee")) } }
    .let(::Players)

fun table(
    inputAction: Action = Action.HIT,
    dealer: Dealer = Dealer(),
    players: Players? = null,
): GameTable =
    GameTable(
        players ?: players(player("kim", inputAction), player("lee", inputAction)),
        dealer,
    )

fun amount(amount: Int): Amount = Amount(amount.toBigDecimal())
fun profit(profit: Int): Profit = Profit(profit.toBigDecimal())
