package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "addCard 함수로 5점인 카드 두개를 추가하면 카드 포인트는 10이다."{
        //given
        val harris = Player("harris")

        //when
        harris.addCard(Card.DIAMOND_5)
        harris.addCard(Card.CLOVER_5)

        //then
        harris.cardPoint() shouldBe Point(10)
    }

    "19점인 플레이어와 21점인 딜러가 있을 때 플레이어가 flip 함수를 호출하면 플레이어의 losingCount와 딜러의 winningCount가 1씩 증가한다."{
        //given
        val dealer = Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.CLOVER_A)))
        val harris = Player("harris", playingCards = PlayingCards(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_9)))
        //when
        harris.losingCount shouldBe 0
        dealer.winningCount shouldBe 0
        harris.flip(dealer)
        //then
        harris.losingCount shouldBe 1
        dealer.winningCount shouldBe 1

    }
})
