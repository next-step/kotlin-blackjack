package blakjack.domain

import blakjack.domain.extension.cards
import blakjack.domain.extension.heart10
import blakjack.domain.extension.heart2
import blakjack.domain.extension.heart3
import blakjack.domain.extension.heart9
import blakjack.domain.extension.heartAce
import blakjack.domain.extension.heartKing
import blakjack.domain.extension.spade10
import blakjack.domain.extension.spadeAce
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
            player.add(heart10)

            it("카드 목록에 카드 1장이 추가된다.") {
                player.cards.size shouldBe 1
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(heart10)
            }
        }
    }

    describe("플레이어 카드 목록 추가 검증") {
        context("플레이어에 카드 목록 1장을 추가하면") {
            val player = Player("홍길동")
            player.add(cards(heart10))

            it("카드 목록에 카드 1장이 추가된다.") {
                player.cards.size shouldBe 1
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(heart10)
            }
        }

        context("플레이어에 카드 목록 2장을 추가하면") {
            val player = Player("홍길동")
            player.add(cards(heart10, heart2))

            it("카드 목록에 카드 2장이 추가된다.") {
                player.cards.size shouldBe 2
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(heart10, heart2)
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
                cards(heart2, heart3) to 5,
                cards(heartAce, heartKing) to 21,
                cards(heartAce, heartKing, spadeAce) to 12,
                cards(heartAce, heart9, spadeAce) to 21,
                cards(heartAce, heart10, heart2) to 13,
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
                cards(heart2, heart3) to true,
                cards(heartAce, heartKing) to true,
                cards(heartAce, heartKing, spadeAce) to true,
                cards(heartAce, heart9, spadeAce) to true,
                cards(heartAce, heart10, heart2) to true,
                cards(heartAce, heart10, heart2, heart9) to false,
                cards(heartAce, heart10, heart2, heart9, spade10) to false,
            )
        ) { (cards, result) ->
            val player = Player("홍길동")
            player.add(cards)

            player.isUnderBlackjackScore shouldBe result
        }
    }
})
