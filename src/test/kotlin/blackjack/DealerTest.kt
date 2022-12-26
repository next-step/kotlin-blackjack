package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러의 카드 포인트가 17보다 작으면 카드를 더 받는다."{
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_10, Card.DIAMOND_6)))
        val cardDeck = CardDeck()

        //when
        val count = dealer.hitUntil(cardDeck)

        //then
        count shouldBe 1
    }

    "딜러의 카드 포인트가 17보다 크거나 같으면 카드를 더 받지 않는다."{
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_10, Card.DIAMOND_7)))
        val cardDeck = CardDeck()

        //when
        val count = dealer.hitUntil(cardDeck)

        //then
        count shouldBe 0
    }

    "딜러의 카드 포인트가 10이고 카드덱이 2, 3, 4, 5 순서라면 17보다 크거나 같을 때 까지 카드를 세 장 더 받는다."{
        //given
        val dealer = Dealer(PlayingCards(mutableSetOf(Card.DIAMOND_4, Card.DIAMOND_6)))
        val cardDeck = CardDeck(mutableListOf(Card.CLOVER_2, Card.CLOVER_3, Card.CLOVER_4, Card.CLOVER_5))

        //when
        val count = dealer.hitUntil(cardDeck)

        //then
        count shouldBe 3
    }
})
