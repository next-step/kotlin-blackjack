package blackjack.domain.card

import blackjack.mock.card
import blackjack.mock.hand
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandScoreTest : DescribeSpec({
    describe("cardScore") {
        context("Ace를 1점으로 계산했을 때 합이 11이하라면") {
            forAll(
                row(hand(card(Rank.TWO), card(Rank.ACE)), 13),
                row(hand(card(Rank.TWO), card(Rank.THREE), card(Rank.FIVE), card(Rank.ACE)), 21),
            ) { hand, expect ->
                it("Ace 1개를 11점으로 계산한다") {
                    HandScore.from(hand).cardScore shouldBe expect
                }
            }
        }

        context("Ace를 1점으로 계산했을 때 합이 11초과라면") {
            val hand = hand(card(Rank.TEN), card(Rank.TEN), card(Rank.ACE))

            it("Ace를 1점으로 계산한다") {
                HandScore.from(hand).cardScore shouldBe 21
            }
        }

        context("Ace가 없는 카드에서 합이 11이하라면") {
            val hand = hand(card(Rank.TWO), card(Rank.THREE))

            it("모든 점수를 그대로 계산한다") {
                HandScore.from(hand).cardScore shouldBe 5
            }
        }
    }

    describe("isGreaterOrEqualToMaxScore") {
        context("21점 미만일 때") {
            val handScore = HandScore(20)

            it("false 반환") {
                handScore.isGreaterOrEqualToMaxScore shouldBe false
            }
        }
        context("21점일 때") {
            val handScore = HandScore(21)

            it("true 반환") {
                handScore.isGreaterOrEqualToMaxScore shouldBe true
            }
        }
        context("21점 초과일 때") {
            val handScore = HandScore(22)

            it("true 반환") {
                handScore.isGreaterOrEqualToMaxScore shouldBe true
            }
        }
    }

    describe("isGreaterCardScoreThan") {
        val score = HandScore(16)
        context("보다 작은 점수를 비교") {
            val result = score isGreaterCardScoreThan 15
            it("참을 반환") {
                result shouldBe true
            }
        }
        context("보다 큰 점수를 비교") {
            val result = score isGreaterCardScoreThan 20
            it("거짓을 반환") {
                result shouldBe false
            }
        }
        context("같은 점수를 비교") {
            val result = score isGreaterCardScoreThan 16
            it("거짓을 반환") {
                result shouldBe false
            }
        }
    }

    describe("isGreaterGameScoreThan") {
        context("보다 작은 점수를 비교") {
            val score = HandScore(16)
            val result = score isGreaterCardScoreThan 15
            it("참을 반환") {
                result shouldBe true
            }
        }

        context("보다 큰 점수를 비교") {
            val score = HandScore(16)
            val result = score isGreaterCardScoreThan 20
            it("거짓을 반환") {
                result shouldBe false
            }
        }
        context("같은 점수를 비교") {
            val score = HandScore(16)
            val result = score isGreaterCardScoreThan 16
            it("거짓을 반환") {
                result shouldBe false
            }
        }
    }

    describe("isBlackJackScore") {
        context("블랙잭 점수인 21점이면") {
            val handScore = HandScore(21)
            it("true 반환") {
                handScore.isBlackJackScore shouldBe true
            }
        }

        context("블랙잭 점수인 21점이 아니면") {
            val handScore = HandScore(22)
            it("false 반환") {
                handScore.isBlackJackScore shouldBe false
            }
        }
    }
})
