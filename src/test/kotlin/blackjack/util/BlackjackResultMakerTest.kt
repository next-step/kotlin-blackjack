package blackjack.util

import blackjack.domain.*
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class BlackjackResultMakerTest : StringSpec({
    "딜러가 플레이어보다 점수가 낮을 때 결과는 딜러의 수익은 -10000 플레이어의 수익은 10000 이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_6, Card.DIAMOND_7)))
        val players =
            listOf(Player("harris", PlayingCards(mutableSetOf(Card.CLOVER_10, Card.CLOVER_7)), BettingAmount(10000)))
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].winningAmount shouldBe -10000
        result[0].totalPoint shouldBe Point(13)
        result[1].winningAmount shouldBe 10000
        result[1].totalPoint shouldBe Point(17)
    }

    "딜러가 플레이어보다 점수가 같을 때 결과는딜러의 수익은 0 플레이어의 수익은 0 이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_6, Card.DIAMOND_7)))
        val players =
            listOf(Player("harris", PlayingCards(mutableSetOf(Card.CLOVER_6, Card.CLOVER_7)), BettingAmount(10000)))
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].winningAmount shouldBe 0
        result[0].totalPoint shouldBe Point(13)
        result[1].winningAmount shouldBe 0
        result[1].totalPoint shouldBe Point(13)
    }

    "딜러가 플레이어보다 점수가 높을 때 결과 딜러의 수익은 10000 플레이어의 수익은 -10000 이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_8, Card.DIAMOND_7)))
        val players =
            listOf(Player("harris", PlayingCards(mutableSetOf(Card.CLOVER_6, Card.CLOVER_7)), BettingAmount(10000)))
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].winningAmount shouldBe 10000
        result[0].totalPoint shouldBe Point(15)
        result[1].winningAmount shouldBe -10000
        result[1].totalPoint shouldBe Point(13)
    }

    "(플레이어 다수) 딜러 19, 플레이어1 bust 베팅 10000, 플레이어2 블랙잭 베팅 20000, 플레이어3 19 베팅 30000 일 때 딜러는 상금이 -20000원이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_10, Card.DIAMOND_9)))
        val players =
            listOf(
                Player(
                    "harris",
                    PlayingCards(mutableSetOf(Card.CLOVER_6, Card.CLOVER_7, Card.CLOVER_Q)),
                    BettingAmount(10000)
                ),
                Player("hurris", PlayingCards(mutableSetOf(Card.CLOVER_A, Card.CLOVER_J)), BettingAmount(20000)),
                Player(
                    "horris",
                    PlayingCards(mutableSetOf(Card.CLOVER_5, Card.CLOVER_8, Card.DIAMOND_6)),
                    BettingAmount(30000)
                )
            )
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].winningAmount shouldBe -20000
        result[0].totalPoint shouldBe Point(19)
        result[1].winningAmount shouldBe -10000
        result[1].totalPoint shouldBe Point(0)
        result[2].winningAmount shouldBe 30000
        result[2].totalPoint shouldBe Point(21)
        result[3].winningAmount shouldBe 0
        result[3].totalPoint shouldBe Point(19)
    }

    "(플레이어 다수) 딜러 bust, 플레이어1 bust 베팅 10000, 플레이어2 bust 20000 일 때 딜러의 상금은 30000원이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_6, Card.DIAMOND_7, Card.DIAMOND_K)))
        val players =
            listOf(
                Player(
                    "harris",
                    PlayingCards(mutableSetOf(Card.CLOVER_J, Card.CLOVER_4, Card.CLOVER_8)),
                    BettingAmount(10000)
                ),
                Player(
                    "hurris",
                    PlayingCards(mutableSetOf(Card.CLOVER_K, Card.CLOVER_6, Card.CLOVER_10)),
                    BettingAmount(20000)
                ),
            )
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        assertSoftly {
            result[0].winningAmount shouldBe 30000
            result[0].totalPoint shouldBe Point(0)
            result[1].winningAmount shouldBe -10000
            result[1].totalPoint shouldBe Point(0)
            result[2].winningAmount shouldBe -20000
            result[2].totalPoint shouldBe Point(0)
        }
    }
})
