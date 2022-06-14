package blackjack.domain.player

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Jack
import blackjack.domain.card.NumberCard
import blackjack.domain.card.Queen
import blackjack.domain.card.Suit
import blackjack.domain.common.Money
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({

    describe("addBaseCards") {
        it("각 플레이어에게 카드를 초기 개수만큼 나누어준다") {
            val players = listOf(Player("yohan"), Player("pang"))

            Players(players).addBaseCards(Dealer(), 2)

            players.forAll { it.cards.cards.size shouldBe 2 }
        }
    }

    describe("hittablePlayers") {
        it("카드를 추가할 수 있는 참가자를 조회한다") {
            val yohan = Player(name = "yohan")
            val pang = Player(name = "pang")
            val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
            pang.changeStatus(PlayerStatus.STAY)

            blackJack.players.hittablePlayers() shouldContainExactly listOf(yohan)
        }
    }

    describe("isEnd") {
        context("모든 참여자가 종료 상태이면") {
            it("true 를 반환한다") {
                val yohan = Player(name = "yohan", playerStatus = PlayerStatus.STAY)
                val pang = Player(name = "pang", playerStatus = PlayerStatus.STAY)

                Players(listOf(yohan, pang)).isEnd() shouldBe true
            }
        }

        context("모든 참여자가 종료 상태가 아니면") {
            it("false 를 반환한다") {
                val yohan = Player(name = "yohan", playerStatus = PlayerStatus.STAY)
                val pang = Player(name = "pang")

                Players(listOf(yohan, pang)).isEnd() shouldBe false
            }
        }
    }

    describe("profit") {
        context("딜러가 주어지면") {
            it("참가자들의 배팅 결과를 알 수 있다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, Jack()),
                        )
                    )
                )

                val yohan = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    ),
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )
                val pang = Player(
                    name = "pang",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                        )
                    ),
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )

                val profits = Players(listOf(yohan, pang)).profit(dealer)

                assertSoftly {
                    profits[yohan] shouldBe Money.of(1000.0)
                    profits[pang] shouldBe Money.of(-1000)
                }
            }
        }
    }
})
