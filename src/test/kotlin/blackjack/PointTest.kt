package blackjack

import blackjack.domain.Point
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PointTest : StringSpec({

    "soft rule: Ace가 있을 떄 포인트가 11보다 크면 값을 그대로 반환한다. Ace의 포인트를 10 더해준다."{
        //given
        val pointOver11 = Point(12)

        //when
        val result = pointOver11.soft()

        //then
        result shouldBe Point(12)

    }
    "soft rule: Ace가 있을 떄 포인트가 11보다 작거나 같으면 Ace의 포인트를 10 더해준다."{
        //given
        val point = Point(11)

        //when
        val result = point.soft()

        //then
        result shouldBe Point(21)
    }
})
