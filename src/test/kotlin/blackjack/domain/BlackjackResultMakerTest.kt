package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackResultMakerTest : StringSpec({
    "딜러가 플레이어보다 점수가 낮을 때 결과는 딜러 패 1 플레이어 승 1 이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_6, Card.DIAMOND_7)))
        val players = listOf(Player("harris", PlayingCards(mutableSetOf(Card.CLOVER_10, Card.CLOVER_7))))
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].losingCount shouldBe 1
        result[1].winningCount shouldBe 1
    }

    "딜러가 플레이어보다 점수가 같을 때 결과는 딜러 무 1 플레이어 무 1 이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_6, Card.DIAMOND_7)))
        val players = listOf(Player("harris", PlayingCards(mutableSetOf(Card.CLOVER_6, Card.CLOVER_7))))
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].tieCount shouldBe 1
        result[1].tieCount shouldBe 1
    }

    "딜러가 플레이어보다 점수가 높을 때 결과는 딜러 승 1 플레이어 패 1 이다." {
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_8, Card.DIAMOND_7)))
        val players = listOf(Player("harris", PlayingCards(mutableSetOf(Card.CLOVER_6, Card.CLOVER_7))))
        //when
        val result = BlackjackResultMaker.result(dealer, players)

        //then
        result[0].winningCount shouldBe 1
        result[1].losingCount shouldBe 1
    }
})
