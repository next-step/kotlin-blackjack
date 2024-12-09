package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeJackCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeQueenCard
import blackjack.CardTextFixtures.spadeSevenCard
import blackjack.CardTextFixtures.spadeSixCard
import blackjack.CardTextFixtures.spadeTwoCard
import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial16Cards
import blackjack.InitialCardsTestFixtures.initial18Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class Dealer2Test : BehaviorSpec({
    given("Dealer 를 생성할 때") {
        `when`("시작 카드가 2장이 아니면") {
            then("IllegalArgumentException 을 던진다") {
                listOf(
                    listOf(
                        spadeKingCard,
                    ),
                    listOf(
                        spadeJackCard,
                        spadeQueenCard,
                        spadeKingCard,
                    ),
                ).forEach { initialCards ->
                    shouldThrow<IllegalArgumentException> {
                        Dealer2(initialCards = initialCards)
                    }
                }
            }
        }

        `when`("시작 카드 조합이 블랙잭이면") {
            val initialCards = blackjackCards
            val sut = Dealer2(initialCards = initialCards)

            then("Dealer 는 종료된 상태가 된다") {
                sut.isFinished() shouldBe true
            }

            then("Dealer 는 블랙잭 상태다") {
                sut.isBlackjack() shouldBe true
            }
        }

        `when`("시작 카드 조합이 블랙잭이 아니면") {
            val initialCards = initial16Cards
            val sut = Dealer2(initialCards = initialCards)

            then("Dealer 는 종료된 상태가 아니다") {
                sut.isFinished() shouldBe false
            }
        }
    }

    given("Dealer 는 종료된 상태일 때") {
        val initialCards = blackjackCards
        val sut = Dealer2(initialCards = initialCards)
        val card = spadeTwoCard

        `when`("히트하려고 하면") {
            then("IllegalStateException 을 던진다") {
                sut.isFinished() shouldBe true
                shouldThrow<IllegalStateException> {
                    sut.hit(card)
                }
            }
        }
    }

    given("Dealer의 시작 카드가 16일 때") {
        val initialCards = initial16Cards

        `when`("Hit 한 카드가 5라면") {
            val sut = Dealer2(initialCards = initialCards)
            val card = spadeFiveCard
            sut.hit(card)

            then("Dealer 는 종료 상태가 아니다") {
                sut.isFinished() shouldBe false
            }
        }
        `when`("Hit 한 카드가 6이라면") {
            val sut = Dealer2(initialCards = initialCards)
            val card = spadeSixCard
            sut.hit(card)

            then("Dealer 는 종료 상태가 된다") {
                sut.isFinished() shouldBe true
            }

            then("Dealer 는 Bust 상태가 된다") {
                sut.isBust() shouldBe true
            }
        }

        `when`("Stay 하면") {
            val sut = Dealer2(initialCards = initialCards)
            sut.stay()

            then("Dealer 는 종료 상태가 된다") {
                sut.isFinished() shouldBe true
            }
        }
    }

    given("Dealer 는") {
        `when`("cards 를 통해") {
            val initialCards = initial16Cards
            val sut = Dealer2(initialCards = initialCards)
            val result = sut.cards

            then("자신이 가진 카드 목록을 반환한다") {
                result.toList() shouldContainExactly initialCards
            }
        }

        `when`("sumOfCards() 를 통해") {
            val initialCards = initial16Cards
            val sut = Dealer2(initialCards = initialCards)
            val result: Int = sut.sumOfCards()

            then("자신이 가진 카드의 합을 반환한다") {
                result shouldBe 16
            }
        }

        `when`("자신의 카드 합이 언더 오버(17) 미만이면") {
            val initialCards = initial16Cards
            val sut = Dealer2(initialCards = initialCards)

            then("shouldHit() 가 true 이다") {
                val result: Boolean = sut.shouldHit()
                result shouldBe true
            }
        }

        `when`("자신의 카드 합이 언더 오버(17) 이상이면") {
            val initialCards =
                listOf(
                    spadeJackCard,
                    spadeSevenCard,
                )
            val sut = Dealer2(initialCards = initialCards)

            then("shouldHit() 가 false 이다") {
                val result: Boolean = sut.shouldHit()
                result shouldBe false
            }
            then("Hit 시 IllegalStateException 을 던진다") {
                val card = spadeAceCard

                shouldThrow<IllegalStateException> {
                    sut.hit(card)
                }
            }
        }

        `when`("Player 와 자신을 비교하여") {
            val sut = Dealer2(initialCards = initial16Cards)
            val player = Player2("y2gcoder", initial18Cards)

            val result: PlayerResult = sut.evaluatePlayerResult(player)

            then("Player 의 승패 결과를 반환할 수 있다") {
                result shouldBe PlayerResult.WIN
            }
        }
    }
})
