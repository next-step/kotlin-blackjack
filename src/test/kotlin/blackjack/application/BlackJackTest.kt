package blackjack.application

import blackjack.domain.CustomDeck
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination.ACE
import blackjack.domain.card.Denomination.FIVE
import blackjack.domain.card.Denomination.FOUR
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.SEVEN
import blackjack.domain.card.Denomination.SIX
import blackjack.domain.card.Denomination.TEN
import blackjack.domain.card.Denomination.THREE
import blackjack.domain.card.Denomination.TWO
import blackjack.domain.card.Point
import blackjack.domain.card.Suit.CLOVER
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.cards
import blackjack.domain.hand
import blackjack.domain.participant.Bust
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Deck
import blackjack.domain.participant.Hittable
import blackjack.domain.participant.Money
import blackjack.domain.participant.Player
import blackjack.domain.participant.Stay
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import blackjack.domain.participant.BlackJack as BLACKJACK

class BlackJackTest : DescribeSpec({

    describe("블랙잭은") {
        it("2명에서 6명 사이의 참가자가 있어야 진행 가능하다") {
            io.kotest.data.forAll(
                table(
                    headers("딜러", "참가자"),
                    row(
                        Dealer(), listOf(Player("1"), Player("2")),
                    ),
                    row(
                        Dealer(), List(6) { Player(it.toString()) }
                    ),
                )
            ) { dealer, players ->
                shouldNotThrowAny {
                    BlackJack(dealer, players)
                }
            }
        }

        it("참가자가 2명에서 6명 사이가 아니라면 진행 가능할 수 없다") {
            io.kotest.data.forAll(
                table(
                    headers("딜러", "참가자"),
                    row(
                        Dealer(), listOf(Player("1")),
                    ),
                    row(
                        Dealer(), List(7) { Player(it.toString()) }
                    ),
                )
            ) { dealer, players ->
                shouldThrowExactly<IllegalArgumentException> {
                    BlackJack(dealer, players)
                }
            }
        }

        it("딜러와 모든 참가자에게 카드를 2장 씩 분배할 수 있다") {
            val deck: Deck = CustomDeck(
                cards(
                    Card(TWO, CLOVER), Card(THREE, HEART),
                    Card(FOUR, HEART), Card(FIVE, SPADE),
                    Card(SIX, DIAMOND), Card(SEVEN, CLOVER)
                )
            )
            val dealer = Dealer(deck)
            val participants = listOf(Player("1"), Player("2"))
            val blackjack = BlackJack(dealer, participants)
            blackjack.distribute()
            dealer.hand shouldBeEqualToComparingFields hand(Card(TWO, CLOVER), Card(THREE, HEART))
            participants[0].hand shouldBeEqualToComparingFields hand(Card(TWO, CLOVER), Card(THREE, HEART))
            participants[1].hand shouldBeEqualToComparingFields hand(Card(SIX, DIAMOND), Card(SEVEN, CLOVER))
        }

        context("카드 분배 후 딜러가 블랙잭이 아니라면") {
            it("블랙잭인 플레이어는 베팅 금액의 1.5배를 받는다") {
                val bettingMoney = Money(100)
                val blackjackPlayer = Player(
                    "1",
                    hand(Card(KING, HEART), Card(ACE, DIAMOND)),
                    state = BLACKJACK,
                    bettingMoney = bettingMoney
                )
                val player1 = Player("2", hand(Card(KING, HEART), Card(SIX, DIAMOND)))
                val player2 = Player("3", hand(Card(KING, HEART), Card(SIX, DIAMOND)))
                val dealer = Dealer(state = Hittable)
                val blackjack = BlackJack(dealer, listOf(blackjackPlayer, player1, player2))
                blackjack.confirmBlackJackPlayer()
                blackjackPlayer.profit shouldBe Money(150)
                dealer.profit shouldBe Money(-150)
            }
        }

        context("카드 분배 후 딜러가 블랙잭이라면") {
            val bettingMoney = Money(100)
            val blackjackPlayer =
                Player("1", hand(Card(KING, HEART), Card(ACE, DIAMOND)), state = BLACKJACK, bettingMoney = bettingMoney)
            val player1 = Player("2", hand(Card(KING, HEART), Card(SIX, DIAMOND)), bettingMoney = Money(100))
            val player2 = Player("3", hand(Card(KING, HEART), Card(SIX, DIAMOND)), bettingMoney = Money(100))
            val dealer = Dealer(state = BLACKJACK)
            val blackjack = BlackJack(dealer, listOf(blackjackPlayer, player1, player2))
            blackjack.confirmBlackJackPlayer()
            it("블랙잭인 플레이어는 베팅 금액을 돌려 받는다") {
                blackjackPlayer.profit shouldBe Money(0)
                dealer.profit shouldBe Money(0)
            }
        }

        it("참가자가 카드를 받을 수 없을 때(Bust 상태) 까지 거래를 진행한다") {
            val deck: Deck = CustomDeck(
                cards(Card(TWO, CLOVER))
            )
            val dealer = Dealer(deck)
            val participant = Player("1", hand(Card(KING, HEART), Card(TEN, DIAMOND)))
            val participants = listOf(participant, Player("2"))
            val blackjack = BlackJack(dealer, participants)
            blackjack.dealWith(participant, { true }, { })
            participant.state shouldBe Bust
        }

        it("참가자가 카드를 받을 수 없을 때(BlackJack 상태) 까지 거래를 진행한다") {
            val deck: Deck = CustomDeck(
                cards(Card(ACE, CLOVER))
            )
            val dealer = Dealer(deck)
            val participant = Player("1", hand(Card(KING, HEART), Card(TEN, DIAMOND)))
            val participants = listOf(participant, Player("2"))
            val blackjack = BlackJack(dealer, participants)
            blackjack.dealWith(participant, { true }, { })
            participant.state shouldBe BLACKJACK
        }

        it("참가자가 카드를 받을 수 없을 때(Stay 상태) 까지 거래를 진행한다") {
            val deck: Deck = CustomDeck(
                cards(Card(ACE, CLOVER))
            )
            val dealer = Dealer(deck)
            val player = Player("1", hand(Card(KING, HEART), Card(SIX, DIAMOND)))
            val players = listOf(player, Player("2"))
            val blackjack = BlackJack(dealer, players)
            blackjack.dealWith(player, { false }, { })
            player.state shouldBe Stay(Point(16))
        }

        it("딜러가 이기는 경우, 플레이어는 베팅 금액을 잃는다") {
            val bettingMoney = Money(100)
            val player1 = Player(
                "1",
                hand(Card(KING, HEART), Card(SIX, DIAMOND)),
                state = Stay(Point(16)),
                bettingMoney = bettingMoney
            )
            val player2 = Player(
                "2",
                hand(Card(KING, HEART), Card(SIX, DIAMOND)),
                state = Stay(Point(16)),
                bettingMoney = bettingMoney
            )
            val dealer = Dealer(state = BLACKJACK)
            val blackjack = BlackJack(dealer, listOf(player1, player2))
            blackjack.matching()
            dealer.profit shouldBe Money(200)
            player1.profit shouldBe Money(-100)
            player2.profit shouldBe Money(-100)
        }
        it("딜러가 지는 경우, 플레이어는 베팅 금액 만큼 수익을 얻는다") {
            val bettingMoney = Money(100)
            val player1 = Player(
                "1",
                hand(Card(KING, HEART), Card(SIX, DIAMOND)),
                state = Stay(Point(16)),
                bettingMoney = bettingMoney
            )
            val player2 = Player(
                "2",
                hand(Card(KING, HEART), Card(SIX, DIAMOND)),
                state = Stay(Point(16)),
                bettingMoney = bettingMoney
            )
            val dealer = Dealer(state = Bust)
            val blackjack = BlackJack(dealer, listOf(player1, player2))
            blackjack.matching()
            dealer.profit shouldBe Money(-200)
            player1.profit shouldBe Money(100)
            player2.profit shouldBe Money(100)
        }
        it("딜러와 비기는 경우, 플레이어는 베팅 금액을 돌려 받는다") {
            val bettingMoney = Money(100)
            val player1 = Player(
                "1",
                hand(Card(KING, HEART), Card(SEVEN, DIAMOND)),
                state = Stay(Point(17)),
                bettingMoney = bettingMoney
            )
            val player2 = Player(
                "2",
                hand(Card(KING, HEART), Card(SEVEN, DIAMOND)),
                state = Stay(Point(17)),
                bettingMoney = bettingMoney
            )
            val dealer = Dealer(state = Stay(Point(17)))
            val blackjack = BlackJack(dealer, listOf(player1, player2))
            blackjack.matching()
            dealer.profit shouldBe Money(0)
            player1.profit shouldBe Money(0)
            player2.profit shouldBe Money(0)
        }
    }
})
