package blackjack

import blackjack.domain.Card
import blackjack.domain.PlayingCards
import blackjack.domain.Point
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayingCardsTest : StringSpec({
    "플레잉 카드가 2Clover 3Spade일 때 카드 점수를 계산하면 5이다." {
        PlayingCards(mutableSetOf(Card.CLOVER_2, Card.SPADE_3)).calculatePoint() shouldBe Point(5)
    }

    "플레잉 카드 중 Ace를 여러개 포함할 경우: Ace를 한 장을 제외한 카드의 합이 10초과이면 Ace의 값을 1로 계산한다." {
        assertSoftly {
            PlayingCards(mutableSetOf(Card.CLOVER_A, Card.CLOVER_10, Card.CLOVER_2)).calculatePoint() shouldBe Point(13)
            PlayingCards(mutableSetOf(Card.CLOVER_A, Card.CLOVER_10, Card.CLOVER_J)).calculatePoint() shouldBe Point(21)
            PlayingCards(mutableSetOf(Card.CLOVER_A, Card.CLOVER_10, Card.HEART_A)).calculatePoint() shouldBe Point(12)
        }
    }

    "플레잉 카드 중 Ace를 여러개 포함할 경우: Ace를 한 장을 제외한 카드의 합이 10이하이면 Ace의 값을 11로 계산한다." {
        assertSoftly {
            PlayingCards(mutableSetOf(Card.CLOVER_A, Card.CLOVER_10)).calculatePoint() shouldBe Point(21)
            PlayingCards(mutableSetOf(Card.CLOVER_A, Card.CLOVER_2)).calculatePoint() shouldBe Point(13)
            PlayingCards(mutableSetOf(Card.CLOVER_A, Card.HEART_A, Card.CLOVER_9)).calculatePoint() shouldBe Point(21)
        }
    }
    "Clover 10, Clover K, Clover 2를 들고있으면 bust이다." {
        PlayingCards(mutableSetOf(Card.CLOVER_10, Card.CLOVER_K, Card.CLOVER_2)).bust() shouldBe true
    }

    "Clover 10, Clover K, Clover A를 들고있으면 bust가 아니다." {
        PlayingCards(mutableSetOf(Card.CLOVER_10, Card.CLOVER_K, Card.CLOVER_A)).bust() shouldBe false
    }
})
