package blackjack.util

import blackjack.domain.*
import io.kotest.core.spec.style.StringSpec
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
})
