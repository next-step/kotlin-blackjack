package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "addCard 함수로 5점인 카드 두개를 추가하면 카드 포인트는 10이다." {
        //given
        val harris = Player("harris")

        //when
        harris.addCard(Card.DIAMOND_5)
        harris.addCard(Card.CLOVER_5)

        //then
        harris.cardPoint() shouldBe Point(10)
    }

    "19점인 플레이어와 21점인 딜러가 있을 때 플레이어가 flip 함수를 호출한 결과는 LOSE이다." {
        //given
        val dealer = Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.CLOVER_A)))
        val harris = Player("harris", playingCards = PlayingCards(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_9)))

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe GameResult.LOSE
    }

    "bust인 플레이어와 bust인 딜러가 있을 때 플레이어는 항상 진다." {
        //given
        val dealer = Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_5, Card.CLOVER_10, Card.CLOVER_K)))
        val harris = Player("harris", playingCards = PlayingCards(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_9, Card.DIAMOND_3)))

        //when
        val result = harris.flip(dealer)

        //then
        dealer.bust() shouldBe true
        harris.bust() shouldBe true
        result shouldBe GameResult.LOSE
    }

    "19점인 플레이어와 bust인 딜러가 있을 때 플레이어는 승리한다." {
        //given
        val dealer = Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_5, Card.CLOVER_10, Card.CLOVER_K)))
        val harris = Player("harris", playingCards = PlayingCards(mutableSetOf(Card.DIAMOND_2, Card.CLOVER_2)))

        //when
        val result = harris.flip(dealer)

        //then
        dealer.bust() shouldBe true
        result shouldBe GameResult.WIN
    }
    "19점인 플레이어와 19점인 딜러가 있을 때 결과는 무승부이다.." {
        //given
        val dealer = Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_9, Card.CLOVER_10)))
        val harris = Player("harris", playingCards = PlayingCards(mutableSetOf(Card.DIAMOND_9, Card.DIAMOND_10)))

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe GameResult.TIE
    }
})
