package blakjack.domain

import blakjack.domain.extension.cards
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PlayerSpec : DescribeSpec({
    describe("플레이어 생성 검증") {
        context("이름이 '홍길동'인 플레이어를 생성하면") {
            val player = Player("홍길동")

            it("이름은 '홍길동'이다.") {
                player.name shouldBe "홍길동"
            }
            it("카드 목록은 비어있다.") {
                player.cards shouldBe Cards.empty()
            }
        }
    }

    describe("플레이어 카드 추가 검증") {
        context("플레이어에 카드 1장을 추가하면") {
            val player = Player("홍길동")
            player.add(Card.SPADE_10)

            it("카드 목록에 카드 1장이 추가된다.") {
                player.cards.size shouldBe 1
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(Card.SPADE_10)
            }
        }
    }

    describe("플레이어 카드 목록 추가 검증") {
        context("플레이어에 카드 목록 1장을 추가하면") {
            val player = Player("홍길동")
            player.add(cards(Card.SPADE_10))

            it("카드 목록에 카드 1장이 추가된다.") {
                player.cards.size shouldBe 1
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(Card.SPADE_10)
            }
        }

        context("플레이어에 카드 목록 2장을 추가하면") {
            val player = Player("홍길동")
            player.add(cards(Card.SPADE_10, Card.HEART_2))

            it("카드 목록에 카드 2장이 추가된다.") {
                player.cards.size shouldBe 2
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(Card.SPADE_10, Card.HEART_2)
            }
        }
    }

    describe("플레이어 점수 검증") {
        context("카드 목록이 비어있으면") {
            val player = Player("홍길동")

            it("점수는 0이다.") {
                player.score shouldBe 0
            }
        }

        withData(
            nameFn = { (cards, sum) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 점수는 ${sum}이다." },
            ts = listOf(
                cards(Card.HEART_2, Card.CLOVER_3) to 5,
                cards(Card.HEART_ACE, Card.DIAMOND_KING) to 21,
                cards(Card.HEART_ACE, Card.DIAMOND_KING, Card.CLOVER_ACE) to 12,
                cards(Card.HEART_ACE, Card.DIAMOND_9, Card.SPADE_ACE) to 21,
                cards(Card.HEART_ACE, Card.HEART_10, Card.CLOVER_2) to 13,
            )
        ) { (cards, sum) ->
            val player = Player("홍길동")
            player.add(cards)

            player.score shouldBe sum
        }
    }

    describe("플레이어 점수 <= 블랙잭(21) 여부 검증") {
        withData(
            nameFn = { (cards, result) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 블랙잭 이하는 ${result}이다." },
            ts = listOf(
                cards() to true,
                cards(Card.HEART_2, Card.CLOVER_3) to true,
                cards(Card.HEART_ACE, Card.DIAMOND_KING) to true,
                cards(Card.HEART_ACE, Card.DIAMOND_KING, Card.CLOVER_ACE) to true,
                cards(Card.HEART_ACE, Card.DIAMOND_9, Card.SPADE_ACE) to true,
                cards(Card.HEART_ACE, Card.HEART_10, Card.CLOVER_2) to true,
                cards(Card.HEART_ACE, Card.HEART_10, Card.CLOVER_2, Card.DIAMOND_9) to false,
                cards(Card.HEART_ACE, Card.HEART_10, Card.CLOVER_2, Card.DIAMOND_9, Card.SPADE_10) to false,
            )
        ) { (cards, result) ->
            val player = Player("홍길동")
            player.add(cards)

            player.isUnderBlackjackScore shouldBe result
        }
    }
})
