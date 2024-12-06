package blackjack.domain

import blackjack.fixtures.createCard
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class UserTest : StringSpec({

    "유저는 카드목록의 점수합이 21점 미만일 경우 카드를 더 받을 수 있다" {
        table(
            headers("ranks"),
            row(listOf("2", "3", "4")),
            row(listOf("4", "5", "10")),
            row(listOf("K", "Q")),
            row(listOf("5", "5", "4", "3", "2")),
        ).forAll { ranks ->
            val cards = Cards(ranks.map { createCard(it) })

            User("홍길동", cards).canReceiveCard() shouldBe true
        }
    }

    "유저는 카드목록의 점수합이 21점 이상할 경우 카드를 더 받을 수 없다" {
        table(
            headers("ranks", "score"),
            row(listOf("J", "Q", "K"), 30),
            row(listOf("A", "K"), 21),
            row(listOf("Q", "10", "A"), 21),
            row(listOf("10", "9", "2"), 21),
            row(listOf("10", "10", "3"), 23),
        ).forAll { ranks, score ->
            val cards = Cards(ranks.map { createCard(it) })

            cards.score shouldBe score
            User("홍길동", cards).canReceiveCard() shouldBe false
        }
    }

    "유저는 카드 2장을 받은 후 점수 합이 21점인 경우 카드를 더 받지 못한다" {
        val cards = Cards(emptyList())
        val user =
            User("홍길동", cards)
                .receiveCard(createCard("A"))
                .receiveCard(createCard("K"))

        user.canReceiveCard() shouldBe false
    }

    "유저는 카드 2장을 받은 후 점수 합이 21점 미만인 경우 카드를 더 받을 수 있다" {
        val cards = Cards(emptyList())
        val user =
            User("홍길동", cards)
                .receiveCard(createCard("10"))
                .receiveCard(createCard("5"))

        user.canReceiveCard() shouldBe true
    }
})
