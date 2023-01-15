package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class JudgmentTest : BehaviorSpec({
    Given("딜러가 버스트라면 ") {
        val dealer = Dealer(
            "dealer",
            Cards(
                mutableListOf(
                    Card(CardSuit.CLOVER, Denomination.KING),
                    Card(CardSuit.CLOVER, Denomination.QUEEN),
                    Card(CardSuit.DIAMOND, Denomination.FIVE)
                )
            )
        )
        When("플레이어가 버스트라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.FIVE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 21점이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 블랙잭이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 21점 미만이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }
    }

    Given("딜러가 21점이고 ") {
        val dealer = Dealer(
            "dealer",
            Cards(
                mutableListOf(
                    Card(CardSuit.CLOVER, Denomination.KING),
                    Card(CardSuit.CLOVER, Denomination.QUEEN),
                    Card(CardSuit.DIAMOND, Denomination.ACE)
                )
            )
        )
        When("플레이어가 버스트라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.FIVE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }

        When("플레이어가 21점이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 블랙잭이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 21점 미만이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }
    }

    Given("딜러가 블랙잭이고 ") {
        val dealer = Dealer(
            "dealer",
            Cards(
                mutableListOf(
                    Card(CardSuit.CLOVER, Denomination.KING),
                    Card(CardSuit.DIAMOND, Denomination.ACE)
                )
            )
        )
        When("플레이어가 버스트라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.FIVE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }

        When("플레이어가 21점이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }

        When("플레이어가 블랙잭이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 21점 미만이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }
    }

    Given("딜러 점수가 17점 이상 21점 미만이고 ") {
        val dealer = Dealer(
            "dealer",
            Cards(
                mutableListOf(
                    Card(CardSuit.CLOVER, Denomination.KING),
                    Card(CardSuit.CLOVER, Denomination.NINE),
                )
            )
        )
        When("플레이어가 버스트라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.FIVE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }

        When("플레이어가 21점이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어가 블랙잭이라면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.DIAMOND, Denomination.ACE)
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }

        When("플레이어 점수가 딜러보다 21점에 가깝지 않다면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.FIVE),
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 패배한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.LOSE))
            }
        }

        When("플레이어 점수가 딜러보다 21점에 가깝다면 ") {
            val player = Player(
                "player",
                Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.KING),
                        Card(CardSuit.CLOVER, Denomination.QUEEN),
                    )
                )
            )
            val result = Judgment.execute(listOf(player, dealer))
            Then("플레이어가 승리한다.") {
                result shouldBe listOf(PlayerResult(name = player.name, result = JudgmentResult.WIN))
            }
        }
    }
})
