package blackjack.domain.card

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandScoreTest : DescribeSpec({
    describe("cardScore") {
        context("Ace를 1점으로 계산했을 때 합이 11이하라면") {
            forAll(
                row(Hand(mutableListOf(Card(Suit.SPADE, Rank.TWO), Card(Suit.CLUB, Rank.ACE))), 13),
                row(
                    Hand(
                        mutableListOf(
                            Card(Suit.SPADE, Rank.TWO),
                            Card(Suit.DIAMOND, Rank.THREE),
                            Card(Suit.DIAMOND, Rank.FIVE),
                            Card(Suit.CLUB, Rank.ACE)
                        )
                    ),
                    21
                ),
            ) { hand, expect ->
                it("Ace 1개를 11점으로 계산한다") {
                    HandScore.from(hand).cardScore shouldBe expect
                }
            }
        }

        context("Ace를 1점으로 계산했을 때 합이 11초과라면") {
            val hand = Hand(
                mutableListOf(
                    Card(Suit.SPADE, Rank.TEN), Card(Suit.SPADE, Rank.TEN), Card(Suit.CLUB, Rank.ACE)
                )
            )
            it("Ace를 1점으로 계산한다") {
                HandScore.from(hand).cardScore shouldBe 21
            }
        }

        context("Ace가 없는 카드에서 합이 11이하라면") {
            val hand = Hand(
                mutableListOf(
                    Card(Suit.SPADE, Rank.TWO), Card(Suit.CLUB, Rank.THREE)
                )
            )
            it("모든 점수를 그대로 계산") {
                HandScore.from(hand).cardScore shouldBe 5
            }
        }
    }

    describe("gameScore") {
        context("BUST 일 때") {
            val hand = HandScore(22)
            hand.isBust shouldBe true

            it("0점 반환") {
                hand.gameScore shouldBe 0
            }
        }

        context("BUST가 아닐 때") {
            val hand = HandScore(21)
            hand.isBust shouldBe false

            it("cardScore 반환") {
                hand.gameScore shouldBe 21
            }
        }
    }

    describe("isBust") {
        context("21을 넘었을 때") {
            val handScore = HandScore(22)
            it("true 반환") {
                handScore.isBust shouldBe true
            }
        }

        context("21을 넘지 않았을 때") {
            val handScore = HandScore(21)
            it("false 반환") {
                handScore.isBust shouldBe false
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

        context("BUST VS other: 더 낮은 cardScore") {
            val score = HandScore(22)
            score.isBust shouldBe true

            val result = score isGreaterGameScoreThan HandScore(10)

            it("거짓 반환") {
                result shouldBe false
            }
        }

        context("더 낮은 cardScore VS other: BUST") {
            val score = HandScore(10)
            score.isBust shouldBe false

            val other = HandScore(22)
            other.isBust shouldBe true

            val result = score isGreaterGameScoreThan other

            it("참 반환") {
                result shouldBe true
            }
        }

        context("BUST VS other: BUST") {
            val score = HandScore(23)
            score.isBust shouldBe true

            val other = HandScore(22)
            other.isBust shouldBe true

            val result = score isGreaterGameScoreThan other

            it("거짓 반환") {
                result shouldBe false
            }
        }
    }

    describe("isSameGameScoreThan") {
        context("같은 점수를 비교") {
            val score = HandScore(16)
            val result = score isSameGameScoreTo HandScore(16)
            it("참을 반환") {
                result shouldBe true
            }
        }

        context("다른 점수를 비교") {
            val score = HandScore(16)
            val result = score isSameGameScoreTo HandScore(20)
            it("거짓 반환") {
                result shouldBe false
            }
        }

        context("다른 cardScore이지만 둘 다 BUST일 때") {
            val score = HandScore(22)
            val other = HandScore(22)
            score.isBust shouldBe true
            other.isBust shouldBe true

            val result = score isSameGameScoreTo other
            it("참 반환") {
                result shouldBe true
            }
        }
    }
})
