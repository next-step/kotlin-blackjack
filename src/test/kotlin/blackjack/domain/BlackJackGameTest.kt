package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "카드를 뽑을 플레이어를 반환한다." {
        val sut = BlackJackGame(
            listOf(Player(participantName = ParticipantName("테스트1")), Player(participantName = ParticipantName("테스트2")))
        )
        val playerName1 = sut.findDrawPlayer()
        playerName1?.name shouldBe ParticipantName("테스트1")

        val playerName2 = sut.findDrawPlayer()
        playerName2?.name shouldBe ParticipantName("테스트2")
    }

    "더이상 카드를 뽑지 않는 플레이어 존재하면 다음 플레이어를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                    draw = false,
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                ),
            )
        )
        val actual = sut.findDrawPlayer()
        actual?.name shouldBe ParticipantName("테스트2")
    }

    "하나의 플레이어가 카드를 계속 뽑는다면 true를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
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
                    participantName = ParticipantName("테스트1"),
                    draw = false,
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
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
                    participantName = ParticipantName("테스트1"),
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                ),
            )
        )

        shouldThrow<IllegalArgumentException> {
            sut.drawCard(ParticipantName("테스트3"))
        }
    }

    "존재하지 않는 플레이어가 카드 뽑기를 멈추면 예외를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                ),
            )
        )

        shouldThrow<IllegalArgumentException> {
            sut.stopDraw(ParticipantName("테스트3"))
        }
    }

    "블랙잭 게임의 최종 결과를 반환한다." {
        val sut = BlackJackGame(
            listOf(
                Dealer(
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
                            CardNumber.KING,
                        ),
                    ),
                ),
                Player(
                    participantName = ParticipantName("테스트1"),
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
                    participantName = ParticipantName("테스트2"),
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

        actual.value[0] shouldBe BlackJackGameResult(
            playerName = "딜러",
            cards = listOf(
                DrawCard(
                    CardSuit.HEARTS,
                    CardNumber.TWO,
                ),
                DrawCard(
                    CardSuit.SPADES,
                    CardNumber.THREE,
                ),
                DrawCard(
                    CardSuit.DIAMONDS,
                    CardNumber.FOUR,
                ),
                DrawCard(
                    CardSuit.CLUBS,
                    CardNumber.KING,
                ),
            ),
            totalValue = 19,
            dealer = true,
        )
        actual.value[1] shouldBe BlackJackGameResult(
            playerName = "테스트1",
            cards = listOf(
                DrawCard(
                    CardSuit.HEARTS,
                    CardNumber.TWO,
                ),
                DrawCard(
                    CardSuit.SPADES,
                    CardNumber.THREE,
                ),
                DrawCard(
                    CardSuit.DIAMONDS,
                    CardNumber.FOUR,
                ),
                DrawCard(
                    CardSuit.CLUBS,
                    CardNumber.ACE,
                ),
            ),
            totalValue = 20,
            dealer = false,
        )
        actual.value[2] shouldBe BlackJackGameResult(
            playerName = "테스트2",
            cards = listOf(
                DrawCard(
                    CardSuit.HEARTS,
                    CardNumber.TWO,
                ),
                DrawCard(
                    CardSuit.SPADES,
                    CardNumber.THREE,
                ),
                DrawCard(
                    CardSuit.DIAMONDS,
                    CardNumber.FOUR,
                ),
                DrawCard(
                    CardSuit.CLUBS,
                    CardNumber.JACK,
                ),
            ),
            totalValue = 19,
            dealer = false,
        )
    }

    "아직 모든 플레이어의 턴이 종료되지 않았다면 결과를 반환할 수 없다." {
        val sut = BlackJackGame(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                    draw = true,
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                    draw = false,
                ),
            )
        )

        shouldThrowWithMessage<IllegalStateException>("모든 플레이어의 턴이 종료되지 않았습니다.") {
            sut.result()
        }
    }
})
