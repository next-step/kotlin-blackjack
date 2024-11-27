import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "카드를 뽑을 플레이어를 반환한다." {
        val sut = BlackJackGame(
            listOf(Player(PlayerName("테스트1")), Player(PlayerName("테스트2")))
        )
        val playerName1 = sut.findDrawPlayer()
        playerName1 shouldBe PlayerName("테스트1")

        val playerName2 = sut.findDrawPlayer()
        playerName2 shouldBe PlayerName("테스트2")
    }

    "더이상 카드를 뽑지 않는 플레이어 존재하면 해당 플레이어는 건너뛰고 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    name = PlayerName("테스트1"),
                    draw = false,
                ),
                Player(
                    name = PlayerName("테스트2"),
                ),
            )
        )
        val actual = sut.findDrawPlayer()
        actual shouldBe PlayerName("테스트2")
    }

    "하나의 플레이어가 카드를 계속 뽑는다면 true를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    name = PlayerName("테스트1"),
                ),
                Player(
                    name = PlayerName("테스트2"),
                    draw = false,
                ),
            )
        )
        val actual = sut.canDrawForAllPlayers()
        actual shouldBe true
    }

    "모든 플레이어가 더이상 카드를 뽑지 않는 경우 false를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    name = PlayerName("테스트1"),
                    draw = false,
                ),
                Player(
                    name = PlayerName("테스트2"),
                    draw = false,
                ),
            )
        )
        val actual = sut.canDrawForAllPlayers()
        actual shouldBe false
    }

    "존재하지 않는 플레이어가 카드를 뽑으면 예외를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    name = PlayerName("테스트1"),
                ),
                Player(
                    name = PlayerName("테스트2"),
                ),
            )
        )

        shouldThrow<IllegalArgumentException> {
            sut.drawCard(PlayerName("테스트3"))
        }
    }

    "존재하지 않는 플레이어가 카드 뽑기를 멈추면 예외를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    name = PlayerName("테스트1"),
                ),
                Player(
                    name = PlayerName("테스트2"),
                ),
            )
        )

        shouldThrow<IllegalArgumentException> {
            sut.stopDraw(PlayerName("테스트3"))
        }
    }

    "블랙잭 게임의 최종 결과를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    name = PlayerName("테스트1"),
                    cards = mutableListOf(
                        Card(
                            CardSuit.HEARTS,
                            CardNumber.TWO,
                        ),
                        Card(
                            CardSuit.SPADES,
                            CardNumber.THREE,
                        ),
                        Card(
                            CardSuit.DIAMONDS,
                            CardNumber.FOUR,
                        ),
                        Card(
                            CardSuit.CLUBS,
                            CardNumber.ACE,
                        ),
                    ),
                    draw = false,
                ),
                Player(
                    name = PlayerName("테스트2"),
                    cards = mutableListOf(
                        Card(
                            CardSuit.HEARTS,
                            CardNumber.TWO,
                        ),
                        Card(
                            CardSuit.SPADES,
                            CardNumber.THREE,
                        ),
                        Card(
                            CardSuit.DIAMONDS,
                            CardNumber.FOUR,
                        ),
                        Card(
                            CardSuit.CLUBS,
                            CardNumber.JACK,
                        ),
                    ),
                    draw = false,
                ),
            )
        )

        val actual = sut.result()

        actual[0] shouldBe BlackJackGameResult("테스트1", 20)
        actual[1] shouldBe BlackJackGameResult("테스트2", 19)
    }
})